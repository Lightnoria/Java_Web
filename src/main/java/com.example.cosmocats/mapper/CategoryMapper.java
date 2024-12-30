package com.example.cosmocats.mapper;

import com.example.cosmocats.dto.CategoryDTO;
import com.example.cosmocats.model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDTO toDTO(Category category);
    Category toEntity(CategoryDTO categoryDTO);
}
