package com.flow.main.controller;

import com.flow.main.dto.controller.hof.get.response.HoFGetUsersResponseDto;
import com.flow.main.service.hof.HoFGetUsersService;
import com.flow.main.service.hof.HoFRankingService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/hall-of-fame")
@RequiredArgsConstructor
public class HallOfFameController {

    private final HoFGetUsersService hoFGetUsersService;

    @GetMapping("/{count}")
    public ResponseEntity<HoFGetUsersResponseDto> getHoFUsers(@PathVariable("count") Long count){
        log.info("count : {}", count);

        return ResponseEntity.ok(hoFGetUsersService.getHoFUsers(count));
    }

}
