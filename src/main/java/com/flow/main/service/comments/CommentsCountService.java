package com.flow.main.service.comments;

import com.flow.main.dto.controller.comment.count.response.CountCommentsAndRepliesResponseDto;
import com.flow.main.dto.jpa.comments.CommentsDto;
import com.flow.main.dto.jpa.posts.PostsDto;
import com.flow.main.service.comments.persistence.CommentsService;
import com.flow.main.service.posts.persistence.PostsService;
import com.flow.main.service.replies.persistence.RepliesService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentsCountService {

    private final PostsService postsService;
    private final CommentsService commentsService;
    private final RepliesService repliesService;

    public CountCommentsAndRepliesResponseDto countCommentsAndReplies(Long postId){

        PostsDto postsDto = postsService.findByPostId(postId);

        List<CommentsDto> commentsDtos = commentsService.findAllByPostId(postsDto.getPostId());
        Long totalCount = (long) commentsDtos.size();
        for (CommentsDto c : commentsDtos){
            Long countReplies = repliesService.countByCommentId(c.getCommentId());
            totalCount += countReplies;
        }

        return CountCommentsAndRepliesResponseDto.builder()
            .commentsAndRepliesCount(totalCount)
            .build();
    }
}
