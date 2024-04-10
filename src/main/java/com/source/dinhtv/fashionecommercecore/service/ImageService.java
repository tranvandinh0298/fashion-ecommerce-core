package com.source.dinhtv.fashionecommercecore.service;

import com.source.dinhtv.fashionecommercecore.exception.FileStorageException;
import com.source.dinhtv.fashionecommercecore.exception.ResourceNotFoundException;
import com.source.dinhtv.fashionecommercecore.http.response.BaseResponse;
import com.source.dinhtv.fashionecommercecore.http.response.SuccessResponse;
import com.source.dinhtv.fashionecommercecore.http.response.payload.ImageResponse;
import com.source.dinhtv.fashionecommercecore.model.Image;
import com.source.dinhtv.fashionecommercecore.repository.ImageRepository;
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

    public BaseResponse<List<ImageResponse>> getAllImages() {
            Specification<Image> spec = combineSpecs(List.of(
                    isNonDeletedRecord()
            ));
            List<Image> images = this.imageRepository.findAll(spec);

            if (images == null || images.isEmpty()) {
                throw new ResourceNotFoundException("Không tìm thấy ảnh nào");
            }

            List<ImageResponse> imagesResponse = new ArrayList<>();
            images.forEach((image) -> {
                imagesResponse.add(new ImageResponse(image.getId(), image.getCaption(), image.getAddress()));
            });

            return new SuccessResponse(imagesResponse);
    }

    public BaseResponse<ImageResponse> getImageById(Integer id) {
        Specification<Image> spec = combineSpecs(List.of(
            hasId(id), isNonDeletedRecord()
        ));
        Image image = this.imageRepository.findOne(spec).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy image cần tìm với id: "+id));

        ImageResponse imagesResponse = new ImageResponse(image.getId(), image.getCaption(), image.getAddress());

        return new SuccessResponse(imagesResponse);

    }

    public BaseResponse<ImageResponse> uploadSingleFile(MultipartFile file) {
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

            return new SuccessResponse(this.saveImage(fileName, fileLocation));
        } catch (Exception ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public BaseResponse softDeleteImage(Integer id) {
        Image existingImage = this.imageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Image not found with id: " + id));

        existingImage.softDelete();

        imageRepository.save(existingImage);

        return new SuccessResponse();
    }

    public BaseResponse deleteImage(Integer id) {
        this.imageRepository.deleteById(id);
        return new SuccessResponse();
    }

    protected ImageResponse saveImage(String fileName, String fileLocation) {
        Image image = new Image(fileName, fileLocation, 1);

        this.imageRepository.save(image);

        ImageResponse imageResponse = new ImageResponse(image.getId(), image.getCaption(), image.getAddress());

        return imageResponse;
    }




}
