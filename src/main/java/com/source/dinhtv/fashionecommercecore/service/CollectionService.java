package com.source.dinhtv.fashionecommercecore.service;

import com.source.dinhtv.fashionecommercecore.exception.ResourceNotFoundException;
import com.source.dinhtv.fashionecommercecore.http.controller.CollectionController;
import com.source.dinhtv.fashionecommercecore.http.response.BaseResponse;
import com.source.dinhtv.fashionecommercecore.http.response.SuccessResponse;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.collection.CollectionDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.CollectionMapper;
import com.source.dinhtv.fashionecommercecore.model.Category;
import com.source.dinhtv.fashionecommercecore.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.source.dinhtv.fashionecommercecore.repository.CategoryRepository.isCollection;
import static com.source.dinhtv.fashionecommercecore.repository.specification.BaseSpecification.*;
import static com.source.dinhtv.fashionecommercecore.utils.CustomConstants.COLLECTION_TYPE;
import static com.source.dinhtv.fashionecommercecore.utils.PaginationUtil.getPagedModel;
import static com.source.dinhtv.fashionecommercecore.utils.PaginationUtil.verifyPageNumAndSize;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class CollectionService {
    @Autowired
    private CategoryRepository collectionRepository;
    @Autowired
    private CollectionMapper collectionMapper;

    public BaseResponse getAllCollections(int pageNum, int pageSize) {
        verifyPageNumAndSize(pageNum,pageSize);

        Specification<Category> specs = combineSpecs(List.of(
                isCollection(),
                isNonDeletedRecord()
        ));
        Page<Category> collectionsPage = collectionRepository.findAll(specs, PageRequest.of(pageNum, pageSize));

        if (collectionsPage.isEmpty()) {
            throw new ResourceNotFoundException("Không tìm thấy bộ sưu tập nào");
        }

        List<EntityModel<CollectionDTO>> CollectionEntities = collectionsPage.stream().map(
                collection -> EntityModel.of(
                        collectionMapper.mapToCollectionDTO(collection),
                        linkTo(methodOn(CollectionController.class).getCollectionById(collection.getId())).withSelfRel())
        ).toList();

        PagedModel<EntityModel<CollectionDTO>> pagedModel = getPagedModel(CollectionEntities,pageNum,pageSize, collectionsPage.getTotalElements(), collectionsPage.getTotalPages());

        return new SuccessResponse(pagedModel);

    }

    public BaseResponse getCollectionById(int id) {
        Category existedCollection = findByIdOrThrowEx(id);

        CollectionDTO collectionDTO = collectionMapper.mapToCollectionDTO(existedCollection);

        Link allCollectionsLink = linkTo(methodOn(CollectionController.class).getAllCollections(0,10)).withRel("allCollections");

        EntityModel<CollectionDTO> collectionEntity = EntityModel.of(collectionDTO, allCollectionsLink);

        return new SuccessResponse(collectionEntity);
    }

    public BaseResponse createCollection(CollectionDTO collectionDTO) {
        Category collection = collectionMapper.mapToCollection(collectionDTO);
        collection.setType(COLLECTION_TYPE);

        collectionRepository.save(collection);

        return new SuccessResponse(collectionMapper.mapToCollectionDTO(collection));
    }

    public BaseResponse updateCollection(int id, CollectionDTO collectionDTO) {
        Category existedCollection = findByIdOrThrowEx(id);

        collectionMapper.updateFromCollectionDTO(collectionDTO, existedCollection);

        collectionRepository.save(existedCollection);

        return new SuccessResponse(collectionMapper.mapToCollectionDTO(existedCollection));
    }

    public BaseResponse softDeleteCollection(int id) {
        Category existedCollection = findByIdOrThrowEx(id);

        existedCollection.softDelete();

        collectionRepository.save(existedCollection);

        return new SuccessResponse();
    }

    public BaseResponse deleteCollection(int id) {
        collectionRepository.deleteById(id);

        return new SuccessResponse();
    }

    private Category findByIdOrThrowEx(int id) {
        Specification<Category> spec = combineSpecs(List.of(
                hasId(id),
                isCollection(),
                isNonDeletedRecord()
        ));
        return this.collectionRepository.findOne(spec).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy bộ sưu tập cần tìm với id: " + id));
    }
}
