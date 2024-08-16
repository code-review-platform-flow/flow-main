package com.flow.main.service.posts;

import com.flow.main.dto.controller.post.keyword.FindByKeywordDto;
import com.flow.main.dto.controller.post.keyword.response.FindByKeywordResponseDto;
import com.flow.main.dto.jpa.posts.PostsDto;
import com.flow.main.mapper.PostsMapper;
import com.flow.main.service.posts.persistence.PostsService;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostsFindByKeywordService {

    private final PostsService postsService;
    private final PostsMapper postsMapper;

    public FindByKeywordResponseDto findPostsByKeyword(String keyword, Long page, Long count){

        Pageable pageable = PageRequest.of(page.intValue() - 1, count.intValue());
        List<PostsDto> postsDtos = postsService.findPostsByKeyword(keyword, pageable);
        List<FindByKeywordDto> findByKeywordDtoList = postsMapper.toFindByKeywordDtoList(postsDtos);
//        findByKeywordDtoList = new ArrayList<>(new LinkedHashSet<>(findByKeywordDtoList));

        List<FindByKeywordDto> listWithoutDuplicates = findByKeywordDtoList.stream()
            .distinct()
            .collect(Collectors.toList());

        return FindByKeywordResponseDto.builder()
            .findByKeywordDtoList(listWithoutDuplicates)
            .build();
    }

}
