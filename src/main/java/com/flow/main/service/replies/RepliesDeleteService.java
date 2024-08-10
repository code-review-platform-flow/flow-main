package com.flow.main.service.replies;

import com.flow.main.dto.controller.comment.replies.request.RepliesDeleteRequestDto;
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
public class RepliesDeleteService {

    private final PostsService postsService;
    private final CommentsService commentsService;
    private final RepliesService repliesService;
    private final UsersService usersService;

    public RepliesDto deleteReplies(Long postId, Long commentId, Long replyId, RepliesDeleteRequestDto repliesDeleteRequestDto){

        /*
        * 1. 유효한 포스트 인지 확인
        * 2. 유효한 댓글인지 확인
        * 3. 사용자 정보 얻어오면서 유효한 사용자인지 확인
        * 4. 해당 사용자가 작성한 답글이어야 함
        * */

        postsService.findByPostId(postId); //check valid post
        commentsService.findByCommentId(commentId); //check valid comment
        UsersDto usersDto = usersService.findByEmail(repliesDeleteRequestDto.getEmail());

        RepliesDto repliesDto = repliesService.findByReplyId(replyId, usersDto.getUserId());

        return repliesService.delete(repliesDto);
    }

}
