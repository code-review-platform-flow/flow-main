package com.flow.main.service.posts;

import com.flow.main.dto.controller.post.tranding.response.GetTrandingPostsResponseDto;
import com.flow.main.dto.jpa.posts.PostsDto;
import com.flow.main.mapper.PostsMapper;
import com.flow.main.service.comments.persistence.CommentsService;
import com.flow.main.service.likes.persistence.LikesService;
import com.flow.main.service.posts.persistence.PostsService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@RequiredArgsConstructor
public class PostsGetTrandingService {

    private final PostsService postsService;
    private final LikesService likesService;
    private final PostsMapper postsMapper;

    public GetTrandingPostsResponseDto getTrandingPosts(Long page,Long count){

        Pageable pageable = PageRequest.of(page.intValue() - 1, count.intValue());
        List<PostsDto> postsDtos = likesService.findPostsOrderByLikeCount(pageable);
        return GetTrandingPostsResponseDto.builder()
            .trandingPostsList(postsMapper.toTrandingPostDtoList(postsDtos))
            .build();
    }
}
