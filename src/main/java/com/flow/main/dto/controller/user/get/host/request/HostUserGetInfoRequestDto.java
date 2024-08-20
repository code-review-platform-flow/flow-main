package com.flow.main.dto.controller.user.get.host.request;

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
public class HostUserGetInfoRequestDto {

    private String visitorEmail;

}
