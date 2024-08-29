package com.flow.main.service.comments;

import com.flow.main.dto.controller.comment.comments.get.CommentsGetDto;
import com.flow.main.dto.controller.comment.comments.get.response.CommentsGetAllResponseDto;
import com.flow.main.dto.controller.comment.replies.get.RepliesGetDto;
import com.flow.main.dto.jpa.comments.CommentsDto;
import com.flow.main.dto.jpa.major.MajorDto;
import com.flow.main.dto.jpa.posts.PostsDto;
import com.flow.main.dto.jpa.replies.RepliesDto;
import com.flow.main.dto.jpa.userinfo.UserInfoDto;
import com.flow.main.dto.jpa.users.UsersDto;
import com.flow.main.service.comments.persistence.CommentsService;
import com.flow.main.service.major.persistence.MajorService;
import com.flow.main.service.posts.PostsDeleteService;
import com.flow.main.service.posts.persistence.PostsService;
import com.flow.main.service.replies.persistence.RepliesService;
import com.flow.main.service.userinfo.persistence.UserInfoService;
import com.flow.main.service.users.persistence.UsersService;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentsGetAllService {

    public final PostsService postsService;
    public final UsersService usersService;
    public final CommentsService commentsService;
    public final UserInfoService userInfoService;
    public final MajorService majorService;
    public final RepliesService repliesService;

    public CommentsGetAllResponseDto getAllCommentsAndReplies(Long postId, String email){

        UsersDto requestUser = email != null ? usersService.findByEmail(email) : null;

        PostsDto postsDto = postsService.findByPostId(postId);
        List<CommentsDto> commentsDtos = commentsService.findAllByPostId(postsDto.getPostId());

        List<CommentsGetDto> commentsGetDtos = new ArrayList<>();
        for(CommentsDto c : commentsDtos){
            UsersDto usersDto = usersService.findByUserId(c.getUserId());
            boolean own = requestUser != null && Objects.equals(requestUser.getUserId(), usersDto.getUserId());
            UserInfoDto userInfoDto = userInfoService.findByUserId(usersDto.getUserId());
            MajorDto majorDto = majorService.findByMajorId(userInfoDto.getMajorId());

            CommentsGetDto commentsGetDto = CommentsGetDto.builder()
                .own(own)
                .commentId(c.getCommentId())
                .profileUrl(userInfoDto.getProfileUrl())
                .userName(userInfoDto.getUserName())
                .majorName(majorDto.getMajorName())
                .studentNumber(userInfoDto.getStudentNumber())
                .commentContent(c.getContent())
                .replies(new ArrayList<>())
                .build();

            commentsGetDtos.add(commentsGetDto);
        }

        for(CommentsGetDto c : commentsGetDtos){
            CommentsDto commentsDto = commentsService.findByCommentId(c.getCommentId());
            List<RepliesDto> repliesDtos = repliesService.findAllByCommentId(commentsDto.getCommentId());

            for (RepliesDto r : repliesDtos){
                UsersDto usersDto = usersService.findByUserId(r.getUserId());
                boolean own = requestUser != null && Objects.equals(requestUser.getUserId(), usersDto.getUserId());
                UserInfoDto userInfoDto = userInfoService.findByUserId(usersDto.getUserId());
                MajorDto majorDto = majorService.findByMajorId(userInfoDto.getMajorId());

                RepliesGetDto repliesGetDto = RepliesGetDto.builder()
                    .own(own)
                    .replyId(r.getReplyId())
                    .profileUrl(userInfoDto.getProfileUrl())
                    .userName(userInfoDto.getUserName())
                    .majorName(majorDto.getMajorName())
                    .studentNumber(userInfoDto.getStudentNumber())
                    .replyContent(r.getContent())
                    .build();

                c.getReplies().add(repliesGetDto);
            }
        }

        return CommentsGetAllResponseDto.builder()
            .comments(commentsGetDtos)
            .build();
    }

}
