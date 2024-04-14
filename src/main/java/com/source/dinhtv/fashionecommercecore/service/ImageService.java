package com.source.dinhtv.fashionecommercecore.service;

import com.source.dinhtv.fashionecommercecore.exception.FileStorageException;
import com.source.dinhtv.fashionecommercecore.exception.GlobalExceptionHandler;
import com.source.dinhtv.fashionecommercecore.exception.ResourceNotFoundException;
import com.source.dinhtv.fashionecommercecore.http.controller.ImageController;
import com.source.dinhtv.fashionecommercecore.http.response.BaseResponse;
import com.source.dinhtv.fashionecommercecore.http.response.SuccessResponse;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.ImageDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.ImageMapper;
import com.source.dinhtv.fashionecommercecore.model.Image;
import com.source.dinhtv.fashionecommercecore.repository.ImageRepository;
import com.source.dinhtv.fashionecommercecore.utils.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

import static com.source.dinhtv.fashionecommercecore.repository.specification.BaseSpecification.*;
import static com.source.dinhtv.fashionecommercecore.utils.CustomConstants.DEFAULT_PAGE_LIMIT;
import static com.source.dinhtv.fashionecommercecore.utils.CustomConstants.DEFAULT_PAGE_NUMBER;
import static com.source.dinhtv.fashionecommercecore.utils.PaginationUtil.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private final Path uploadDir = Paths.get("uploads").toAbsolutePath().normalize();

    public ImageService() {
        try {
            Files.createDirectories(this.uploadDir);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public BaseResponse getAllImages(Integer pageNum, Integer pageSize) {
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
                        ImageMapper.MAPPER.mapToImageDTO(image),
                        linkTo(methodOn(ImageController.class).getImage(image.getId())).withSelfRel()
                )
        ).toList();

                // Create pagination metadata
//        PagedModel.PageMetadata metadata = getPageMetaData(imagePage);

        // Add pagination links
//        List<Link> paginationLinks = linkTo(methodOn(ImageController.class).getAllImages(1, limit));

//        PagedModel<EntityModel<ImageDTO>> pagedModel = PagedModel.of(imageEntities, metadata);
//        paginationLinks.forEach(pagedModel::add);

//        Link link = linkTo(.getAllImages()));

//        CollectionModel<EntityModel<ImageDTO>> imageCollection = CollectionModel.of(imageEntities, link);

//        PagedResourcesAssembler

        PagedModel<EntityModel<ImageDTO>> pagedModel = getPagedModel(imageEntities, pageNum, pageSize, imagePage.getTotalElements(), imagePage.getTotalPages());;

        return new SuccessResponse(pagedModel);
    }

    public BaseResponse getImageById(Integer id) {
        Specification<Image> spec = combineSpecs(List.of(
                hasId(id), isNonDeletedRecord()
        ));
        Image image = this.imageRepository.findOne(spec).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy image cần tìm với id: " + id));

        ImageDTO imageDTO = ImageMapper.MAPPER.mapToImageDTO(image);

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

    protected ImageDTO saveImage(String fileName, String fileLocation) {
        Image image = new Image(fileName, fileLocation, 1);

        this.imageRepository.save(image);

        return ImageMapper.MAPPER.mapToImageDTO(image);
    }

}
