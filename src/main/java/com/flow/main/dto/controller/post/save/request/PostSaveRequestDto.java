package com.flow.main.dto.controller.post.save.request;

import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostSaveRequestDto {
    private String email;
    private String category;
    private ArrayList<String> tags;
    private String title;
    private String content;
}
