package com.flow.main.dto.controller.hof.get.response;

import com.flow.main.dto.controller.hof.get.UserEmail;
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
public class HoFGetUsersResponseDto {

    List<UserEmail> userList;

}
