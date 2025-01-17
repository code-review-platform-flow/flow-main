package com.flow.main.dto.jpa.users;

import com.flow.main.dto.jpa.comments.CommentsDto;
import com.flow.main.dto.jpa.posts.PostsDto;
import com.flow.main.dto.jpa.replies.RepliesDto;
import com.flow.main.dto.jpa.userinfo.UserInfoDto;
import com.flow.main.dto.jpa.usersessions.UserSessionsDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsersDto {
    private Long userId;
    private String email;
    private String password;
    private int version;
}
