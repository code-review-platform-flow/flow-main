package com.flow.main.service.replies;

import com.flow.main.dto.controller.comment.replies.write.request.RepliesWriteRequestDto;
import com.flow.main.dto.jpa.comments.CommentsDto;
import com.flow.main.dto.jpa.replies.RepliesDto;
import com.flow.main.dto.jpa.userinfo.UserInfoDto;
import com.flow.main.dto.jpa.users.UsersDto;
import com.flow.main.event.alarm.AlarmEvent;
import com.flow.main.event.alarm.AlarmEventPublisher;
import com.flow.main.service.comments.persistence.CommentsService;
import com.flow.main.service.posts.persistence.PostsService;
import com.flow.main.service.replies.persistence.RepliesService;
import com.flow.main.service.userinfo.persistence.UserInfoService;
import com.flow.main.service.users.persistence.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RepliesWriteService {

    private final PostsService postsService;
    private final CommentsService commentsService;
    private final UsersService usersService;
    private final UserInfoService userInfoService;
    private final RepliesService repliesService;
    private final AlarmEventPublisher alarmEventPublisher;

    public RepliesDto writeReplies(Long postId, Long commentId, RepliesWriteRequestDto repliesWriteRequestDto){

        postsService.findByPostId(postId); //check valid post
        CommentsDto commentsDto = commentsService.findByCommentId(commentId);
        UsersDto usersDto = usersService.findByEmail(repliesWriteRequestDto.getEmail());
        UserInfoDto userInfoDto = userInfoService.findByUserId(usersDto.getUserId());

        RepliesDto repliesDto = RepliesDto.builder()
            .commentId(commentsDto.getCommentId())
            .userId(usersDto.getUserId())
            .content(repliesWriteRequestDto.getReplyContent())
            .build();

        RepliesDto savedRepliesDto = repliesService.save(repliesDto);

        // 알람 이벤트 발행
        alarmEventPublisher.alarm(AlarmEvent.builder()
            .userId(commentsDto.getUserId()) // 댓글 작성자
            .alarmType("REPLY")
            .message(userInfoDto.getUserName() + "님이 답글을 작성하셨습니다")
            .isRead(false)
            .referenceId(commentsDto.getPostId())
            .referenceTable("posts")
            .build());

        return savedRepliesDto;
    }

}
