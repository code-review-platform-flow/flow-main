package com.flow.main.service.posttags;

import com.flow.main.dto.controller.post.PostTagsNameDto;
import com.flow.main.dto.jpa.posttags.PostTagsDto;
import com.flow.main.dto.jpa.tags.TagsDto;
import com.flow.main.service.posttags.persistence.PostTagsService;
import com.flow.main.service.tags.persistence.TagsService;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostTagsSaveAllService {

    private final PostTagsService postTagsService;
    private final TagsService tagsService;

    public void saveAll(Long postId, ArrayList<PostTagsNameDto> postTagsNameDtos){
        for(PostTagsNameDto tag : postTagsNameDtos){
            String tagName = tag.getTagName();
            TagsDto tagsDto = tagsService.saveOrFindByTagName(tagName);

            PostTagsDto postTagsDto = PostTagsDto.builder()
                .postId(postId)
                .tagId(tagsDto.getTagId())
                .build();
            postTagsService.save(postTagsDto);
        }
    }
}
