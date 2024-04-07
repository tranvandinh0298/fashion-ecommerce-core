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
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    public List<Image> getAllImages() {
        return this.imageRepository.findAll();
    }

    public Optional<Image> getImageById(Integer id) {
        return this.imageRepository.findById(id);
    }

    public Optional<Image> getImageByCaption(String name) {
        return this.imageRepository.findByCaption(name);
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

            return this.saveImage(fileName, fileLocation);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    protected Image saveImage(String fileName, String fileLocation) {
        Image image = new Image(fileName, fileLocation, 1);

        this.imageRepository.save(image);

        return image;
    }

    public boolean softDeleteImage(Integer id) {
        this.imageRepository.
    }

    public boolean deleteImage(Integer id) {
        this.imageRepository.deleteById(id);
        return true;
    }






}
