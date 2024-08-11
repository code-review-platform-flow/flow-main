package com.flow.main.service.replies;

import com.flow.main.dto.controller.comment.replies.modify.request.RepliesModifyRequestDto;
import com.flow.main.dto.jpa.replies.RepliesDto;
import com.flow.main.dto.jpa.users.UsersDto;
import com.flow.main.service.comments.persistence.CommentsService;
import com.flow.main.service.posts.persistence.PostsService;
import com.flow.main.service.replies.persistence.RepliesService;
import com.flow.main.service.users.persistence.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RepliesModifyService {

    private final PostsService postsService;
    private final CommentsService commentsService;
    private final RepliesService repliesService;
    private final UsersService usersService;

    public RepliesDto updateReplies(Long postId, Long commentId, Long replyId, RepliesModifyRequestDto repliesModifyRequestDto){

        postsService.findByPostId(postId); //check valid post
        commentsService.findByCommentId(commentId); //check valid comment
        UsersDto usersDto = usersService.findByEmail(repliesModifyRequestDto.getEmail());

        RepliesDto repliesDto = repliesService.findByReplyId(replyId, usersDto.getUserId());
        repliesDto.setContent(repliesModifyRequestDto.getReplyContent());

        return repliesService.save(repliesDto);
    }

}
