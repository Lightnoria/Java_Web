package com.example.cosmocats.mapper;

import com.example.cosmocats.dto.OrderDTO;
import com.example.cosmocats.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(target = "products", source = "products")
    OrderDTO toDTO(Order order);
    Order toEntity(OrderDTO orderDTO);
}
