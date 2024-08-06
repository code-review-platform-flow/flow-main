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
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final UsersService usersService;
    private final CategoriesService categoriesService;
    private final TagsService tagsService;
    private final PostsService postsService;
    private final PostTagsService postTagsService;

    public ResponseEntity<PostSaveResponseDto> save(PostSaveRequestDto postSaveRequestDto){

        /*
        * 1. email을 이용하여 사용자 정보 얻어오기
        * 2. category를 이용하여 카테고리 Dto 얻어오기
        * 3. tag를 이용하여 있는 지 확인 후 없다면 새로 추가하기
        * 4. Post 저장하기
        * 5. PostTags 저장하기
        * */

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

}
