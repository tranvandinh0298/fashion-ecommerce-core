package com.source.dinhtv.fashionecommercecore.service;

import com.source.dinhtv.fashionecommercecore.exception.FileStorageException;
import com.source.dinhtv.fashionecommercecore.model.Image;
import com.source.dinhtv.fashionecommercecore.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;
    private final Path uploadDir = Paths.get("uploads").toAbsolutePath().normalize();

    public ImageService() {
        try {
            Files.createDirectories(this.uploadDir);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public Image uploadSingleFile(MultipartFile file) {
        Map<String, String> data = new HashMap<>();

        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        data.put("fileName", fileName);

        try {
            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.uploadDir.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            String fileLocation = targetLocation.toString();

            data.put("fileLocation", targetLocation.toString());

            Image image = new Image(fileName, fileLocation, 1);

            this.imageRepository.save(image);

            return image;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

//    public HashMap<String, String> uploadMultipleFiles(MultipartFile files) {
//        Map<String, String> data = new HashMap<>();
//
//        // Normalize file name
//        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//
//        try {
//            // Check if the file's name contains invalid characters
//            if(fileName.contains("..")) {
//                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
//            }
//
//            // Copy file to the target location (Replacing existing file with the same name)
//            Path targetLocation = this.fileStorageLocation.resolve(fileName);
//            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
//
//            return fileName;
//        } catch (IOException ex) {
//            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
//        }
//    }
}
