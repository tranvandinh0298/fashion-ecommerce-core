package com.source.dinhtv.fashionecommercecore.service;

import com.source.dinhtv.fashionecommercecore.exception.FileStorageException;
import com.source.dinhtv.fashionecommercecore.exception.GlobalExceptionHandler;
import com.source.dinhtv.fashionecommercecore.exception.ResourceNotFoundException;
import com.source.dinhtv.fashionecommercecore.http.controller.ImageController;
import com.source.dinhtv.fashionecommercecore.http.response.BaseResponse;
import com.source.dinhtv.fashionecommercecore.http.response.SuccessResponse;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.image.ImageDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.image.ImageMapper;
import com.source.dinhtv.fashionecommercecore.model.Image;
import com.source.dinhtv.fashionecommercecore.repository.ImageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

import static com.source.dinhtv.fashionecommercecore.repository.specification.BaseSpecification.*;
import static com.source.dinhtv.fashionecommercecore.utils.PaginationUtil.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private ImageMapper imageMapper;
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private final Path uploadDir = Paths.get("uploads").toAbsolutePath().normalize();

    public ImageService() {
        try {
            Files.createDirectories(this.uploadDir);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public BaseResponse getAllImages(int pageNum, int pageSize) {
        verifyPageNumAndSize(pageNum,pageSize);

        Specification<Image> spec = combineSpecs(List.of(
                isNonDeletedRecord()
        ));
        Page<Image> imagePage = this.imageRepository.findAll(spec, PageRequest.of(pageNum,pageSize));

        if (imagePage.isEmpty()) {
            throw new ResourceNotFoundException("Không tìm thấy ảnh nào");
        }

        List<EntityModel<ImageDTO>> imageEntities = imagePage.stream().map(
                image -> EntityModel.of(
                        imageMapper.mapToImageDTO(image),
                        linkTo(methodOn(ImageController.class).getImage(image.getId())).withSelfRel()
                )
        ).toList();

        PagedModel<EntityModel<ImageDTO>> pagedModel = getPagedModel(imageEntities, pageNum, pageSize, imagePage.getTotalElements(), imagePage.getTotalPages());;

        return new SuccessResponse(pagedModel);
    }

    public BaseResponse getImageById(int id) {
        Image existingImage = findByIdOrThrowEx(id);

        ImageDTO imageDTO = imageMapper.mapToImageDTO(existingImage);

        Link allImagesLink = linkTo(methodOn(ImageController.class).getAllImages(0,10)).withRel("allImages");

        EntityModel<ImageDTO> imageEntity = EntityModel.of(imageDTO, allImagesLink);

        return new SuccessResponse(imageEntity);

    }

    public BaseResponse uploadSingleFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.uploadDir.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            String fileLocation = targetLocation.toString();

            return new SuccessResponse(this.saveImage(fileName, fileLocation));
        } catch (Exception ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public BaseResponse softDeleteImage(int id) {
        Image existingImage = findByIdOrThrowEx(id);

        existingImage.softDelete();

        imageRepository.save(existingImage);

        return new SuccessResponse();
    }

    public BaseResponse deleteImage(int id) {
        this.imageRepository.deleteById(id);
        return new SuccessResponse();
    }

    private ImageDTO saveImage(String fileName, String fileLocation) {
        Image image = new Image(fileName, fileLocation, 1);

        this.imageRepository.save(image);

        return imageMapper.mapToImageDTO(image);
    }

    private Image findByIdOrThrowEx(int id) {
        Specification<Image> spec = combineSpecs(List.of(
                hasId(id),
                isNonDeletedRecord()
        ));
        return imageRepository.findOne(spec).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy image cần tìm với id: " + id));
    }

}
