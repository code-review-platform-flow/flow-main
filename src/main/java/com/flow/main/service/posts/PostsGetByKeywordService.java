package com.flow.main.service.posts;

import com.flow.main.dto.controller.post.keyword.response.FindByKeywordResponseDto;
import com.flow.main.dto.jpa.posts.PostsDto;
import com.flow.main.mapper.PostsMapper;
import com.flow.main.service.posts.persistence.PostsService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostsGetByKeywordService {

    private final PostsService postsService;
    private final PostsMapper postsMapper;

    public FindByKeywordResponseDto findPostsByKeyword(String keyword, Long page, Long count){

        Pageable pageable = PageRequest.of(page.intValue() - 1, count.intValue());
        List<PostsDto> postsDtosWithDuplicates = postsService.findPostsByKeyword(keyword, pageable);
        List<PostsDto> postsDtosWithoutDuplicates = postsDtosWithDuplicates.stream()
            .distinct()  // 중복을 제거하는 distinct() 메서드 사용
            .toList();
        return FindByKeywordResponseDto.builder()
            .findByKeywordDtoList(postsMapper.toFindByKeywordDtoList(postsDtosWithoutDuplicates))
            .build();
    }

}
