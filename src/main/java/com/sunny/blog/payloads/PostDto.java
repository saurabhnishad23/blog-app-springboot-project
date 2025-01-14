package com.sunny.blog.payloads;

import com.sunny.blog.entities.Comment;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
@Getter
@Setter
@NoArgsConstructor
public class PostDto {

    private int postId;

    @NotEmpty
    @Size(min = 2, max = 100, message = "size limit between 2 to 100")
    private String postTitle;

    @NotEmpty
    @Size(min= 10, max = 10000, message = "maintain size limit between 10 to 10000")
    private String content;

    private String imageName;

    private Date addedDate;

    private CategoryDto category;

    private UserDto user;

    private Set<CommentDto> comments = new HashSet<>();
}
