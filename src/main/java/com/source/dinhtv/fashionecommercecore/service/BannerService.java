package com.source.dinhtv.fashionecommercecore.service;

import com.source.dinhtv.fashionecommercecore.exception.ResourceNotFoundException;
import com.source.dinhtv.fashionecommercecore.http.controller.BannerController;
import com.source.dinhtv.fashionecommercecore.http.controller.CategoryController;
import com.source.dinhtv.fashionecommercecore.http.request.pagination.SearchRequest;
import com.source.dinhtv.fashionecommercecore.http.response.BaseResponse;
import com.source.dinhtv.fashionecommercecore.http.response.SuccessResponse;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.banner.BannerDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.category.CategoryDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.category.CategoryWithImageDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.banner.BannerMapper;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.category.CategoryMapper;
import com.source.dinhtv.fashionecommercecore.model.Banner;
import com.source.dinhtv.fashionecommercecore.model.Category;
import com.source.dinhtv.fashionecommercecore.repository.BannerRepository;
import com.source.dinhtv.fashionecommercecore.repository.CategoryRepository;
import com.source.dinhtv.fashionecommercecore.repository.specification.SearchSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.source.dinhtv.fashionecommercecore.repository.CategoryRepository.isRegularCategory;
import static com.source.dinhtv.fashionecommercecore.repository.specification.BaseSpecification.*;
import static com.source.dinhtv.fashionecommercecore.utils.CustomConstants.CATEGORY_TYPE;
import static com.source.dinhtv.fashionecommercecore.utils.PaginationUtil.getPagedModel;
import static com.source.dinhtv.fashionecommercecore.utils.PaginationUtil.verifyPageNumAndSize;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BannerService {
    @Autowired
    private BannerRepository bannerRepository;
    @Autowired
    private BannerMapper bannerMapper;

    //    public BaseResponse getAllBanners(int pageNum, int pageSize) {
//        verifyPageNumAndSize(pageNum,pageSize);
//
//        Specification<Banner> specs = combineSpecs(List.of(
//                isNonDeletedRecord()
//        ));
//        Page<Banner> bannerPage = bannerRepository.findAll(specs, PageRequest.of(pageNum, pageSize));
//
//        if (bannerPage.isEmpty()) {
//            throw new ResourceNotFoundException("Không tìm thấy banner nào");
//        }
//
//        List<EntityModel<BannerDTO>> BannerEntities = bannerPage.stream().map(
//                banner -> EntityModel.of(
//                        bannerMapper.mapToBannerDTO(banner),
//                        linkTo(methodOn(BannerController.class).getBannerById(banner.getId())).withSelfRel())
//        ).toList();
//
//        PagedModel<EntityModel<BannerDTO>> pagedModel = getPagedModel(BannerEntities,pageNum,pageSize, bannerPage.getTotalElements(), bannerPage.getTotalPages());
//
//        return new SuccessResponse(pagedModel);
//
//    }
    public BaseResponse getAllBanners(SearchRequest request) {
        SearchSpecification<Banner> specs = new SearchSpecification<>(request);
        Pageable pageable = SearchSpecification.getPageable(request.getPage(), request.getSize());
        Page<Banner> bannerPage = bannerRepository.findAll(specs, pageable);

        List<EntityModel<BannerDTO>> BannerEntities = bannerPage.stream().map(
                banner -> EntityModel.of(
                        bannerMapper.mapToBannerDTO(banner),
                        linkTo(methodOn(BannerController.class).getBannerById(banner.getId())).withSelfRel())
        ).toList();

        PagedModel<EntityModel<BannerDTO>> pagedModel = getPagedModel(BannerEntities, bannerPage.getNumber(), bannerPage.getSize(), bannerPage.getTotalElements(), bannerPage.getTotalPages());

        return new SuccessResponse(pagedModel);
    }

    public BaseResponse getBannerById(int id) {
        Banner existedBanner = findByIdOrThrowEx(id);

        BannerDTO bannerDTO = bannerMapper.mapToBannerDTO(existedBanner);

        Link allBannersLink = linkTo(methodOn(BannerController.class).getAllBanners(new SearchRequest())).withRel("allBanners");

        EntityModel<BannerDTO> categoryEntity = EntityModel.of(bannerDTO, allBannersLink);

        return new SuccessResponse(bannerDTO);
    }

    public BaseResponse createBanner(BannerDTO bannerDTO) {
        Banner banner = bannerMapper.mapToBanner(bannerDTO);

        bannerRepository.save(banner);

        return new SuccessResponse(bannerMapper.mapToBannerDTO(banner));
    }

    public BaseResponse updateBanner(int id, BannerDTO bannerDTO) {
        Banner existedBanner = findByIdOrThrowEx(id);

        bannerMapper.updateFromBannerDTO(bannerDTO, existedBanner);

        bannerRepository.save(existedBanner);

        return new SuccessResponse(bannerMapper.mapToBannerDTO(existedBanner));
    }

    public BaseResponse softDeleteBanner(int id) {
        Banner existedBanner = findByIdOrThrowEx(id);

        existedBanner.softDelete();

        bannerRepository.save(existedBanner);

        return new SuccessResponse();
    }

    public BaseResponse deleteBanner(int id) {
        bannerRepository.deleteById(id);

        return new SuccessResponse();
    }

    private Banner findByIdOrThrowEx(int id) {
        Specification<Banner> spec = combineSpecs(List.of(
                hasId(id),
                isNonDeletedRecord()
        ));
        return bannerRepository.findOne(spec).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy thể loại cần tìm với id: " + id));
    }
}
