package com.flow.main.service.comments.persistence;

import com.flow.main.dto.jpa.comments.CommentsDto;
import com.flow.main.entity.CommentsEntity;
import com.flow.main.mapper.CommentsMapper;
import com.flow.main.repository.CommentsRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentsService {

    private final CommentsRepository commentsRepository;
    private final CommentsMapper commentsMapper;

    @Transactional
    public CommentsDto save(CommentsDto commentsDto){
        CommentsEntity commentsEntity = commentsMapper.toEntity(commentsDto);
        return commentsMapper.toDto(commentsRepository.save(commentsEntity));
    }

    @Transactional(readOnly = true)
    public CommentsDto findByCommentId(Long commentId){
        return commentsMapper.toDto(commentsRepository.findByCommentId(commentId)
            .orElseThrow(() -> new EntityNotFoundException("Comments not found with commentId : " + commentId)));
    }

    @Transactional(readOnly = true)
    public CommentsDto findByCommentId(Long commentId, Long userId){
        return commentsMapper.toDto(commentsRepository.findByCommentId(commentId, userId)
            .orElseThrow(() -> new EntityNotFoundException("Comments not found with commentId : " + commentId + " , userId : " + userId)));
    }

    @Transactional(readOnly = true)
    public List<CommentsDto> findAllByPostId(Long postId){
        List<CommentsEntity> commentsEntities = commentsRepository.findAllByPostId(postId)
            .orElse(Collections.emptyList());
        return commentsMapper.toListDto(commentsEntities);
    }

    @Transactional
    public CommentsDto delete(CommentsDto commentsDto){
        CommentsEntity commentsEntity = commentsMapper.toEntity(commentsDto);
        commentsEntity.markDeleted();
        return commentsMapper.toDto(commentsRepository.save(commentsEntity));
    }
}
