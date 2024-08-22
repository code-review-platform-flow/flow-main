package com.flow.main.service.comments;

import com.flow.main.dto.controller.comment.comments.write.request.CommentsWriteRequestDto;
import com.flow.main.dto.controller.comment.comments.write.response.CommentsWriteResponseDto;
import com.flow.main.dto.jpa.comments.CommentsDto;
import com.flow.main.dto.jpa.posts.PostsDto;
import com.flow.main.dto.jpa.userinfo.UserInfoDto;
import com.flow.main.dto.jpa.users.UsersDto;
import com.flow.main.event.alarm.AlarmEvent;
import com.flow.main.event.alarm.AlarmEventPublisher;
import com.flow.main.service.comments.persistence.CommentsService;
import com.flow.main.service.posts.persistence.PostsService;
import com.flow.main.service.userinfo.persistence.UserInfoService;
import com.flow.main.service.users.persistence.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentsWriteService {

    private final PostsService postsService;
    private final UsersService usersService;
    private final UserInfoService userInfoService;
    private final CommentsService commentsService;
    private final AlarmEventPublisher alarmEventPublisher;

    public CommentsWriteResponseDto writeComments(Long postId, CommentsWriteRequestDto commentsWriteRequestDto){

        PostsDto postsDto = postsService.findByPostId(postId);
        UsersDto usersDto = usersService.findByEmail(commentsWriteRequestDto.getEmail());
        UserInfoDto userInfoDto = userInfoService.findByUserId(usersDto.getUserId());

        CommentsDto commentsDto = CommentsDto.builder()
            .postId(postsDto.getPostId())
            .userId(usersDto.getUserId())
            .content(commentsWriteRequestDto.getCommentContent())
            .build();

        CommentsDto savedCommentsDto = commentsService.save(commentsDto);

        // 알람 이벤트 발행
        alarmEventPublisher.alarm(AlarmEvent.builder()
            .userId(postsDto.getUserId()) // 글 작성자
            .alarmType("COMMENT")
            .message(userInfoDto.getUserName() + "님이 댓글을 작성하셨습니다")
            .isRead(false)
            .referenceId(postsDto.getPostId())
            .referenceTable("posts")
            .build());

        return CommentsWriteResponseDto.builder()
            .commentId(savedCommentsDto.getCommentId())
            .build();
    }

}
