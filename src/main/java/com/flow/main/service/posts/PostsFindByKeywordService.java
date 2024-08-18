package com.flow.main.service.posts;

import com.flow.main.dto.controller.post.keyword.FindByKeywordDto;
import com.flow.main.dto.controller.post.keyword.response.FindByKeywordResponseDto;
import com.flow.main.dto.jpa.categories.CategoriesDto;
import com.flow.main.dto.jpa.posts.PostsDto;
import com.flow.main.dto.jpa.posttags.PostTagsDto;
import com.flow.main.dto.jpa.tags.TagsDto;
import com.flow.main.entity.PostsEntity;
import com.flow.main.mapper.PostsMapper;
import com.flow.main.service.categories.persistence.CategoriesService;
import com.flow.main.service.posts.persistence.PostsService;
import com.flow.main.service.posttags.persistence.PostTagsService;
import com.flow.main.service.tags.persistence.TagsService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostsFindByKeywordService {

    private final PostsService postsService;
    private final CategoriesService categoriesService;
    private final TagsService tagsService;
    private final PostTagsService postTagsService;
    private final PostsMapper postsMapper;

    public FindByKeywordResponseDto findPostsByKeyword(String keyword, Long page, Long count){

        HashMap<PostsDto, Integer> postsScores = new HashMap<>();

        List<Object[]> postsByKeywordWithCount = postsService.findPostsByKewordWithCount(keyword);
        postsByKeywordWithCount.forEach(object -> postsScores.put(postsMapper.toDto((PostsEntity) object[0]), (Integer) object[1]));

        categoriesService.findCategoryByKeyword(keyword)
            .ifPresent(categoriesDto -> postsService.findPostsByCategoryId(categoriesDto.getCategoryId())
                .forEach(postsDto -> postsScores.put(postsDto, postsScores.getOrDefault(postsDto, 1) + 1)));

        List<TagsDto> tagsWithKeyword = tagsService.findTagsByKeyword(keyword);
        List<PostsDto> postsWithTagList = new ArrayList<>();
        tagsWithKeyword.forEach(tagsDto -> postTagsService.findAllByTagId(tagsDto.getTagId())
                .forEach(postTagsDto -> postsWithTagList.add(postsService.findByPostId(postTagsDto.getPostId()))));

        postsWithTagList.forEach(postsDto -> postsScores.put(postsDto, postsScores.getOrDefault(postsDto, 1) + 1));

        List<Map.Entry<PostsDto, Integer>> sortedPosts = new ArrayList<>(postsScores.entrySet());
        sortedPosts.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

        List<Map.Entry<PostsDto, Integer>> pagedPosts = sortedPosts.stream()
            .skip((page - 1) * count)
            .limit(count)
            .toList();

        List<FindByKeywordDto> findByKeywordDtoList = new ArrayList<>();
        pagedPosts.forEach(postsDtoLongEntry ->
            findByKeywordDtoList.add(FindByKeywordDto.builder()
                .postId(postsDtoLongEntry.getKey().getPostId())
                .build()));

        return FindByKeywordResponseDto.builder()
            .findByKeywordDtoList(findByKeywordDtoList)
            .build();
    }

}
