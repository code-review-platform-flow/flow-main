package com.flow.main.service.replies.persistence;

import com.flow.main.dto.jpa.replies.RepliesDto;
import com.flow.main.entity.RepliesEntity;
import com.flow.main.mapper.RepliesMapper;
import com.flow.main.repository.RepliesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RepliesService {

    private final RepliesRepository repliesRepository;
    private final RepliesMapper repliesMapper;

    public RepliesDto save(RepliesDto repliesDto){
        RepliesEntity repliesEntity = repliesMapper.toEntity(repliesDto);
        return repliesMapper.toDto(repliesRepository.save(repliesEntity));
    }

}
