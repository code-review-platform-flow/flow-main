package com.flow.main.service;

import com.flow.main.dto.controller.post.save.request.PostSaveRequestDto;
import com.flow.main.dto.controller.post.save.response.PostSaveResponseDto;
import com.flow.main.dto.jpa.categories.CategoriesDto;
import com.flow.main.dto.jpa.posts.PostsDto;
import com.flow.main.dto.jpa.posttags.PostTagsDto;
import com.flow.main.dto.jpa.tags.TagsDto;
import com.flow.main.dto.jpa.users.UsersDto;
import com.flow.main.service.categories.persistence.CategoriesService;
import com.flow.main.service.posts.persistence.PostsService;
import com.flow.main.service.posttags.persistence.PostTagsService;
import com.flow.main.service.tags.persistence.TagsService;
import com.flow.main.service.users.persistence.UsersService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final UsersService usersService;
    private final CategoriesService categoriesService;
    private final TagsService tagsService;
    private final PostsService postsService;
    private final PostTagsService postTagsService;

    public ResponseEntity<PostSaveResponseDto> save(PostSaveRequestDto postSaveRequestDto){

        UsersDto usersDto = usersService.findByEmail(postSaveRequestDto.getEmail());
        CategoriesDto categoriesDto = categoriesService.findByCategoryName(
            postSaveRequestDto.getCategory());

        try {
            PostsDto postsDto = PostsDto.builder()
                .userId(usersDto.getUserId())
                .categoryId(categoriesDto.getCategoryId())
                .title(postSaveRequestDto.getTitle())
                .content(postSaveRequestDto.getContent())
                .build();
            PostsDto savedDto = postsService.save(postsDto);

            for(String tag : postSaveRequestDto.getTags()){
                TagsDto tagsDto = tagsService.saveOrFindByTagName(tag);

                PostTagsDto postTagsDto = PostTagsDto.builder()
                    .postId(savedDto.getPostId())
                    .tagId(tagsDto.getTagId())
                    .build();

                postTagsService.save(postTagsDto);
            }
            PostSaveResponseDto postSaveResponseDto = PostSaveResponseDto.builder()
                .postId(savedDto.getPostId())
                .build();

            return ResponseEntity.ok(postSaveResponseDto);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<PostSaveResponseDto> modify(PostSaveRequestDto postSaveRequestDto, Long postId){

        PostsDto postsDto = postsService.findByPostId(postId);

        CategoriesDto categoriesDto = categoriesService.findByCategoryName(postSaveRequestDto.getCategory());

        postsDto.setCategoryId(categoriesDto.getCategoryId());
        postsDto.setTitle(postSaveRequestDto.getTitle());
        postsDto.setContent(postSaveRequestDto.getContent());

        PostsDto savedDto = postsService.save(postsDto);

        Set<Long> oldTagsId = new HashSet<>();
        Set<Long> newTagsId = new HashSet<>();

        List<PostTagsDto> postTagsDtos = postTagsService.findListByPostId(postId);

        for (PostTagsDto pt : postTagsDtos) {
            oldTagsId.add(pt.getTagId());
        }

        for (String tag : postSaveRequestDto.getTags()) {
            TagsDto tagsDto = tagsService.saveOrFindByTagName(tag);
            newTagsId.add(tagsDto.getTagId());
        }

        Set<Long> intersection = new HashSet<>(oldTagsId);
        intersection.retainAll(newTagsId);
        oldTagsId.removeAll(intersection);
        newTagsId.removeAll(intersection);

        for (Long tagId : oldTagsId) {
            PostTagsDto postTagsDto = postTagsService.findByPostIdAndTagId(postId, tagId);
            postTagsService.delete(postTagsDto);
        }

        for (Long tagId : newTagsId) {
            PostTagsDto postTagsDto = PostTagsDto.builder()
                .tagId(tagId)
                .postId(postId)
                .build();
            postTagsService.reuseOrSavePostTags(postTagsDto);
        }

        PostSaveResponseDto postSaveResponseDto = PostSaveResponseDto.builder()
            .postId(savedDto.getPostId())
            .build();
        return ResponseEntity.ok(postSaveResponseDto);
    }

}
