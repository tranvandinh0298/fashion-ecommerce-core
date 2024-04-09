package com.source.dinhtv.fashionecommercecore.service;

import com.source.dinhtv.fashionecommercecore.exception.FileStorageException;
import com.source.dinhtv.fashionecommercecore.exception.ResourceNotFoundException;
import com.source.dinhtv.fashionecommercecore.model.Image;
import com.source.dinhtv.fashionecommercecore.repository.ImageRepository;
import com.source.dinhtv.fashionecommercecore.repository.specification.BaseSpecification;
import com.source.dinhtv.fashionecommercecore.repository.specification.ImageSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

import static com.source.dinhtv.fashionecommercecore.repository.specification.BaseSpecification.*;

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
        try {
            Specification<Image> spec = combineSpecs(List.of(
                    isNonDeletedRecord()
            ));
            return this.imageRepository.findAll(spec);
        } catch (Exception ex) {
            // Handle exceptions appropriately
            ex.printStackTrace();
            return null;
        }
    }

    public Image getImageById(Integer id) {
        try {
            Specification<Image> spec = combineSpecs(List.of(
                    hasId(id),
                    isNonDeletedRecord()
            ));
            return this.imageRepository.findOne(spec).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy image cần tìm với id: "+id));
        } catch (Exception ex) {
            // Handle exceptions appropriately
            ex.printStackTrace();
            return null;
        }
    }

    public Image getImageByCaption(String caption) {
        return this.imageRepository.findByCaption(caption).orElseThrow(() -> new ResourceNotFoundException("Image not found with caption: " + caption));
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
        Image existingImage = this.imageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Image not found with id: " + id));

        existingImage.softDelete();

        imageRepository.save(existingImage);

        return false;
    }

    public boolean deleteImage(Integer id) {
        this.imageRepository.deleteById(id);
        return true;
    }






}
