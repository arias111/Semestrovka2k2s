package com.spring.basics.dto;

import com.spring.basics.models.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDto {

    private Long id;
    private String name;
    private String imageName;

    public static CategoryDto from(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .imageName(category.getImageName())
                .build();
    }

    public static List<CategoryDto> from(List<Category> categories) {
        return categories.stream().map(CategoryDto::from).collect(Collectors.toList());
    }
}
