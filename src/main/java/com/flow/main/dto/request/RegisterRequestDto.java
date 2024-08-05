package com.flow.main.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequestDto {

    @NonNull
    private String email;

    @NonNull
    private String password;

    private String schoolName;
    private String majorName;
    private String studentNumber;

}
