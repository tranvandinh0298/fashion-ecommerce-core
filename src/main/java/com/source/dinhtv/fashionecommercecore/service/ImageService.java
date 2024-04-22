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
import static com.source.dinhtv.fashionecommercecore.utils.CustomConstants.RECORD_STATUS_ACTIVE;
import static com.source.dinhtv.fashionecommercecore.utils.PaginationUtil.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private ImageMapper imageMapper;

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

    public BaseResponse getAllImagesWithoutPagination() {
        Specification<Image> spec = combineSpecs(List.of(
                isNonDeletedRecord()
        ));
        List<Image> images = this.imageRepository.findAll(spec);
        if (images.isEmpty()) {
            throw new ResourceNotFoundException("Không tìm thấy ảnh nào");
        }
        List<ImageDTO> imageDTOs = images.stream().map(
                image -> imageMapper.mapToImageDTO(image)
        ).toList();

        return new SuccessResponse(imageDTOs);
    }

    public BaseResponse getImageById(int id) {
        Image existingImage = findByIdOrThrowEx(id);

        ImageDTO imageDTO = imageMapper.mapToImageDTO(existingImage);

        Link allImagesLink = linkTo(methodOn(ImageController.class).getAllImages(0,10)).withRel("allImages");

        EntityModel<ImageDTO> imageEntity = EntityModel.of(imageDTO, allImagesLink);

        return new SuccessResponse(imageEntity);

    }

    public BaseResponse createImage(ImageDTO imageDTO) {
        Image image = imageMapper.mapToImage(imageDTO);
        image.setStatus(RECORD_STATUS_ACTIVE);
        imageRepository.save(image);

        return new SuccessResponse(imageMapper.mapToImageDTO(image));
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

    private Image findByIdOrThrowEx(int id) {
        Specification<Image> spec = combineSpecs(List.of(
                hasId(id),
                isNonDeletedRecord()
        ));
        return imageRepository.findOne(spec).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy image cần tìm với id: " + id));
    }


}
