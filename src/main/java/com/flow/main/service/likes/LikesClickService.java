package com.flow.main.service.likes;

import com.flow.main.dto.controller.like.request.LikeClickRequestDto;
import com.flow.main.dto.controller.like.response.LikeCLickResponseDto;
import com.flow.main.dto.jpa.likes.LikesDto;
import com.flow.main.dto.jpa.posts.PostsDto;
import com.flow.main.dto.jpa.userinfo.UserInfoDto;
import com.flow.main.dto.jpa.users.UsersDto;
import com.flow.main.event.alarm.AlarmEvent;
import com.flow.main.event.alarm.AlarmEventPublisher;
import com.flow.main.service.likes.persistence.LikesService;
import com.flow.main.service.posts.persistence.PostsService;
import com.flow.main.service.userinfo.persistence.UserInfoService;
import com.flow.main.service.users.persistence.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikesClickService {

    private final LikesService likesService;
    private final UsersService usersService;
    private final PostsService postsService;
    private final UserInfoService userInfoService;
    private final AlarmEventPublisher alarmEventPublisher;

    public LikeCLickResponseDto clickLike(Long postId, LikeClickRequestDto likeClickRequestDto){

        PostsDto postsDto = postsService.findByPostId(postId);
        UsersDto usersDto = usersService.findByEmail(likeClickRequestDto.getEmail());
        UserInfoDto userInfoDto = userInfoService.findByUserId(usersDto.getUserId());
        likesService.checkUserAlreadyClickLike(usersDto.getUserId(), postsDto.getPostId(), true);

        LikesDto likesDto = likesService.findUseYnFalseOrCreateEmptyEntity(usersDto.getUserId(), postsDto.getPostId(), false);

        if(!likesDto.isUseYn()) { // reClick
            return likesService.reuse(likesDto);
        }

        likesDto.setUserId(usersDto.getUserId());
        likesDto.setPostId(postsDto.getPostId());

        LikesDto savedLikesDto = likesService.save(likesDto);

        // 알람 이벤트 발행
        alarmEventPublisher.alarm(AlarmEvent.builder()
            .userId(postsDto.getUserId()) // 좋아요
            .alarmType("LIKE")
            .message(userInfoDto.getUserName() + "님이 좋아요를 눌렀습니다")
            .isRead(false)
            .referenceId(postsDto.getPostId())
            .referenceTable("posts")
            .build());

        return LikeCLickResponseDto.builder()
            .likeId(savedLikesDto.getLikeId())
            .build();
    }

}
