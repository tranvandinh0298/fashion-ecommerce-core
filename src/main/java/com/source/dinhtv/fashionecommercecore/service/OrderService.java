package com.source.dinhtv.fashionecommercecore.service;

import com.source.dinhtv.fashionecommercecore.exception.ResourceNotFoundException;
import com.source.dinhtv.fashionecommercecore.http.controller.OrderController;
import com.source.dinhtv.fashionecommercecore.http.request.OrderFilter;
import com.source.dinhtv.fashionecommercecore.http.request.BaseFilter;
import com.source.dinhtv.fashionecommercecore.http.request.search.SearchRequest;
import com.source.dinhtv.fashionecommercecore.http.response.BaseResponse;
import com.source.dinhtv.fashionecommercecore.http.response.SuccessResponse;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.order.OrderDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.order.OrderWithUserAndShippingDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.order.OrderMapper;
import com.source.dinhtv.fashionecommercecore.model.Order;
import com.source.dinhtv.fashionecommercecore.repository.OrderRepository;
import com.source.dinhtv.fashionecommercecore.repository.specification.DynamicalSpecification;
import com.source.dinhtv.fashionecommercecore.repository.specification.SearchSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.source.dinhtv.fashionecommercecore.repository.specification.BaseSpecification.*;
import static com.source.dinhtv.fashionecommercecore.utils.PaginationUtil.getPagedModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderMapper orderMapper;

    public BaseResponse getAllOrders(SearchRequest request) {
        new OrderFilter(request).convertFilterKey();

        SearchSpecification<Order> specs = new DynamicalSpecification<>(request, List.of(isNonDeletedRecord()));

        Page<Order> orderPage = orderRepository.findAll(specs, PageRequest.of(request.getPage(), request.getSize()));

        List<EntityModel<OrderWithUserAndShippingDTO>> OrderEntities = orderPage.stream().map(
                order -> EntityModel.of(
                        orderMapper.mapToOrderWithUserAndShippingDTO(order),
                        linkTo(methodOn(OrderController.class).getOrderById(order.getId())).withSelfRel())
        ).toList();

        PagedModel<EntityModel<OrderWithUserAndShippingDTO>> pagedModel = getPagedModel(OrderEntities, orderPage.getNumber(), orderPage.getSize(), orderPage.getTotalElements(), orderPage.getTotalPages());

        return new SuccessResponse(pagedModel);
    }

    public BaseResponse getAllOrdersWithoutPagination(SearchRequest request) {
        // filter object
        BaseFilter orderFilter = new OrderFilter(request);
        orderFilter.convertFilterKey();
        orderFilter.appendSpecs(isNonDeletedRecord());

        // specs
        SearchSpecification<Order> specs = new DynamicalSpecification<>(request, orderFilter.getAdditionalSpecs());

        // orders
        List<Order> orders = orderRepository.findAll(specs);

        // convert entities to DTOs
        List<EntityModel<OrderWithUserAndShippingDTO>> OrderEntities = orders.stream().map(
                order -> EntityModel.of(
                        orderMapper.mapToOrderWithUserAndShippingDTO(order),
                        linkTo(methodOn(OrderController.class).getOrderById(order.getId())).withSelfRel())
        ).toList();

        return new SuccessResponse(OrderEntities);
    }

    public BaseResponse getOrderById(int id) {
        Order existedOrder = findByIdOrThrowEx(id);

        OrderWithUserAndShippingDTO orderDTO = orderMapper.mapToOrderWithUserAndShippingDTO(existedOrder);

        Link allOrdersLink = linkTo(methodOn(OrderController.class).getAllOrders(new SearchRequest())).withRel("allOrders");

        EntityModel<OrderWithUserAndShippingDTO> orderEntity = EntityModel.of(orderDTO, allOrdersLink);

        return new SuccessResponse(orderEntity);
    }

    public BaseResponse createOrder(OrderDTO orderDTO) {
        Order order = orderMapper.mapToOrder(orderDTO);

        orderRepository.save(order);

        return new SuccessResponse(orderMapper.mapToOrderDTO(order));
    }

    public BaseResponse updateOrder(int id, OrderDTO orderDTO) {
        Order existedOrder = findByIdOrThrowEx(id);

        orderMapper.updateFromOrderDTO(orderDTO, existedOrder);

        orderRepository.save(existedOrder);

        return new SuccessResponse(orderMapper.mapToOrderDTO(existedOrder));
    }

    public BaseResponse softDeleteOrder(int id) {
        Order existedOrder = findByIdOrThrowEx(id);

        existedOrder.softDelete();

        orderRepository.save(existedOrder);

        return new SuccessResponse(true);
    }

    public BaseResponse deleteOrder(int id) {
        orderRepository.deleteById(id);

        return new SuccessResponse();
    }

    protected Order findByIdOrThrowEx(int id) {
        Specification<Order> spec = combineSpecs(List.of(
                hasId(id),
                isNonDeletedRecord()
        ));
        return orderRepository.findOne(spec).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy order cần tìm với id: " + id));
    }
}
