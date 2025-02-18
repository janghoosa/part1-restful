package com.sprint.mission.part1restful.service;

import com.sprint.mission.part1restful.domain.Comment;
import com.sprint.mission.part1restful.dto.CreateCommentRequestDto;
import com.sprint.mission.part1restful.dto.CommentResponseDto;
import com.sprint.mission.part1restful.dto.UpdateCommentRequestDto;
import com.sprint.mission.part1restful.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    CommentRepository commentRepository;

    @Override
    public CommentResponseDto createComment(CreateCommentRequestDto createCommentRequestDto) {

        Long userId = createCommentRequestDto.getUserId();
        Long postId = createCommentRequestDto.getPostId();
        String content = createCommentRequestDto.getContent();

        Comment comment = new Comment(userId, postId, content);

        commentRepository.save(comment);

        return new CommentResponseDto(comment);
    }

    @Override
    public CommentResponseDto findById(Long id) {

        Comment comment = commentRepository.findById(id).orElseThrow();

        return new CommentResponseDto(comment);
    }

    @Override
    public List<CommentResponseDto> findByPostId(Long postId) {

        List<Comment> comments = commentRepository.findByPostId(postId);

        return comments.stream()
                .map(CommentResponseDto::new)
                .toList();
    }

    @Override
    public CommentResponseDto updateComment(Long id, UpdateCommentRequestDto updateCommentRequestDto) {

        String content = updateCommentRequestDto.getContent();

        Comment comment = commentRepository.findById(id).orElseThrow();
        comment.updateContent(content);

        commentRepository.save(comment);

        return new CommentResponseDto(comment);
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}
