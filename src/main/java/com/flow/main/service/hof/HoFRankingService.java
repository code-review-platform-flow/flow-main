package com.flow.main.service.hof;

import com.flow.main.dto.jpa.hof.HallOfFameDto;
import com.flow.main.dto.jpa.users.UsersDto;
import com.flow.main.service.comments.persistence.CommentsService;
import com.flow.main.service.hof.persistence.HallOfFameService;
import com.flow.main.service.posts.persistence.PostsService;
import com.flow.main.service.replies.persistence.RepliesService;
import com.flow.main.service.users.persistence.UsersService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HoFRankingService {

    private final CommentsService commentsService;
    private final RepliesService repliesService;
    private final HallOfFameService hallOfFameService;

    @Scheduled(cron = "0 0 0 * * *", zone = "Asia/Seoul")
    public void ranking(){

        Map<Long, Integer> commentCount = commentsService.findUsersWithCommentCountOrdered();
        Map<Long, Integer> replyCount = repliesService.findUsersWithReplyCountOrdered();

        Map<Long, Integer> combinedCount = new HashMap<>(commentCount);
        replyCount.forEach((key, value) -> combinedCount.merge(key, value, Integer::sum));

        Map<Long, Integer> sortedCombinedCount = combinedCount.entrySet().stream()
            .sorted(Map.Entry.<Long, Integer>comparingByValue().reversed())
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                (e1, e2) -> e1,
                LinkedHashMap::new
            ));

        AtomicInteger rankCounter = new AtomicInteger(1);

        sortedCombinedCount.entrySet().stream()
            .limit(9)
            .forEach(set -> {
                HallOfFameDto hallOfFameDto = HallOfFameDto.builder()
                    .userId(set.getKey())
                    .dateAwarded(LocalDate.now(ZoneId.of("Asia/Seoul")))
                    .rank(rankCounter.getAndIncrement())
                    .build();

                hallOfFameService.save(hallOfFameDto);
            });
    }
}
