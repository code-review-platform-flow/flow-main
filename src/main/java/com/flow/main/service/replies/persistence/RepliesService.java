package com.flow.main.service.replies.persistence;

import com.flow.main.dto.jpa.replies.RepliesDto;
import com.flow.main.entity.RepliesEntity;
import com.flow.main.mapper.RepliesMapper;
import com.flow.main.repository.RepliesRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RepliesService {

    private final RepliesRepository repliesRepository;
    private final RepliesMapper repliesMapper;

    @Transactional
    public RepliesDto save(RepliesDto repliesDto){
        RepliesEntity repliesEntity = repliesMapper.toEntity(repliesDto);
        return repliesMapper.toDto(repliesRepository.save(repliesEntity));
    }

    @Transactional(readOnly = true)
    public RepliesDto findByReplyId(Long replyId, Long userId){
        RepliesEntity repliesEntity = repliesRepository.findByReplyId(replyId, userId)
            .orElseThrow(() -> new EntityNotFoundException("Replies not found with replyId : " + replyId + " , userId : " + userId));
        return repliesMapper.toDto(repliesEntity);
    }

    @Transactional(readOnly = true)
    public List<RepliesDto> findAllByCommentId(Long commentId){
        List<RepliesEntity> repliesEntities = repliesRepository.findAllByCommentId(commentId)
            .orElse(Collections.emptyList());
        return repliesMapper.toListDto(repliesEntities);
    }

    @Transactional(readOnly = true)
    public Long countByCommentId(Long commentId){
        return repliesRepository.countByCommentId(commentId);
    }

    @Transactional
    public RepliesDto delete(RepliesDto repliesDto){
        RepliesEntity repliesEntity = repliesMapper.toEntity(repliesDto);
        repliesEntity.markDeleted();
        return repliesMapper.toDto(repliesRepository.save(repliesEntity));
    }
}
