package com.flow.main.controller;

import com.flow.main.dto.controller.follow.cancel.request.FollowCancelRequestDto;
import com.flow.main.dto.controller.follow.cancel.response.FollowCancelResponseDto;
import com.flow.main.dto.controller.follow.follow.request.FollowRequestDto;
import com.flow.main.dto.controller.follow.follow.response.FollowResponseDto;
import com.flow.main.dto.controller.follow.get.followee.response.FolloweeGetResponseDto;
import com.flow.main.dto.controller.follow.get.follower.response.FollowerGetResponseDto;
import com.flow.main.service.follows.FollowsCancelService;
import com.flow.main.service.follows.FollowsFollowService;
import com.flow.main.service.follows.FollowsFolloweesGetService;
import com.flow.main.service.follows.FollowsFollowersGetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/follow")
@RequiredArgsConstructor
public class FollowController {
    private final FollowsFollowersGetService followsFollowersGetService;
    private final FollowsFolloweesGetService followsFolloweesGetService;
    private final FollowsFollowService followsFollowService;
    private final FollowsCancelService followsCancelService;

    @GetMapping("/follower-list/{email}")
    public ResponseEntity<FollowerGetResponseDto> followerList(@PathVariable("email") String email) {
        log.info("email : {}", email);

        return ResponseEntity.ok(followsFollowersGetService.getFollowers(email));
    }

    @GetMapping("/followee-list/{email}")
    public ResponseEntity<FolloweeGetResponseDto> followeeList(@PathVariable("email") String email) {
        log.info("email : {}", email);

        return ResponseEntity.ok(followsFolloweesGetService.getFollowees(email));
    }

    @PostMapping("")
    public ResponseEntity<FollowResponseDto> follow(@RequestBody final FollowRequestDto followRequestDto){
        log.info("follower email : {}", followRequestDto.getFollowerEmail());
        log.info("followee email : {}", followRequestDto.getFolloweeEmail());

        return ResponseEntity.ok(followsFollowService.follow(followRequestDto));
    }

    @DeleteMapping("/cancel")
    public ResponseEntity<FollowCancelResponseDto> followCancel(@RequestBody final
        FollowCancelRequestDto followCancelRequestDto){
        log.info("follower email : {}", followCancelRequestDto.getFollowerEmail());
        log.info("followee email : {}", followCancelRequestDto.getFolloweeEmail());

        return ResponseEntity.ok(followsCancelService.followCancel(followCancelRequestDto));
    }
}
