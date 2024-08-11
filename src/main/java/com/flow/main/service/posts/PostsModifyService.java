package com.flow.main.service.posts;

import com.flow.main.dto.controller.post.TagsNameDto;
import com.flow.main.dto.controller.post.modify.request.PostModifyRequestDto;
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
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostsModifyService {

    private final UsersService usersService;
    private final PostsService postsService;
    private final CategoriesService categoriesService;
    private final PostTagsService postTagsService;
    private final TagsService tagsService;

    public PostsDto modify(Long postId, PostModifyRequestDto postModifyRequestDto){

        UsersDto usersDto = usersService.findByEmail(postModifyRequestDto.getEmail());
        CategoriesDto categoriesDto = categoriesService.findByCategoryName(postModifyRequestDto.getCategory());

        PostsDto postsDto = postsService.findByPostId(postId, usersDto.getUserId());
        postsDto.setCategoryId(categoriesDto.getCategoryId());
        postsDto.setTitle(postModifyRequestDto.getTitle());
        postsDto.setContent(postModifyRequestDto.getContent());
        PostsDto savedDto = postsService.save(postsDto);

        List<PostTagsDto> postTagsDtos = postTagsService.findAllByPostId(postId);

        Set<Long> oldTagsId = new HashSet<>();
        Set<Long> newTagsId = new HashSet<>();

        for (PostTagsDto pt : postTagsDtos) {
            oldTagsId.add(pt.getTagId());
        }

        for (TagsNameDto tag : postModifyRequestDto.getTags()) {
            TagsDto tagsDto = tagsService.saveOrFindByTagName(tag.getTagName());
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

        return savedDto;
    }

}
