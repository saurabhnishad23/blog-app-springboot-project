package com.sunny.blog.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
    @NotBlank
    private int categoryId;
    @NotBlank
    @Size(min = 3, max = 20, message = "char length limit between 3 to 20")
    private String categoryTitle;
    @NotBlank
    @Size(max = 50)
    private String categoryDescription;
}
