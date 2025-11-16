package com.education.finalorm.mapper;

import org.mapstruct.*;
import com.education.finalorm.dto.request.CategoryRequest;
import com.education.finalorm.dto.response.CategoryResponse;
import com.education.finalorm.entity.Category;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {
    Category toEntity(CategoryRequest request);

    CategoryResponse toResponse(Category category);

    List<CategoryResponse> toResponseList(List<Category> categories);
}
