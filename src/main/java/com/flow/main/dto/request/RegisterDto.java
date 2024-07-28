package com.flow.main.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class RegisterDto {

    @NonNull
    private String email;

    @NonNull
    private String password;

    @JsonProperty("schoolName")
    private String schoolName;

    @JsonProperty("studentNumber")
    private String studentNumber;

    @JsonProperty("majorName")
    private String majorName;

}
