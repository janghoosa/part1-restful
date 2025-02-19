package com.sprint.mission.part1restful.service;

import com.sprint.mission.part1restful.domain.Comment;
import com.sprint.mission.part1restful.dto.CommentResponseDto;
import com.sprint.mission.part1restful.dto.CreateCommentRequestDto;
import com.sprint.mission.part1restful.dto.UpdateCommentRequestDto;
import com.sprint.mission.part1restful.repository.CommentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
//    private final UserService userService;
//    private final PostService postService;

    @Override
    @Transactional
    public CommentResponseDto createComment(CreateCommentRequestDto createCommentRequestDto) {

        Long userId = createCommentRequestDto.getUserId();
        Long postId = createCommentRequestDto.getPostId();
        String content = createCommentRequestDto.getContent();

        Comment comment = new Comment(userId, postId, content);

        commentRepository.save(comment);

        return CommentResponseDto.fromEntity(comment);
    }

    @Override
    public CommentResponseDto findById(Long id) {

        Comment comment = commentRepository.findById(id).orElseThrow();

        return CommentResponseDto.fromEntity(comment);
    }

    @Override
    public List<CommentResponseDto> findByPostId(Long postId) {

        List<Comment> comments = commentRepository.findByPostId(postId);

        return comments.stream()
                .map(CommentResponseDto::fromEntity)
                .toList();
    }

    @Override
    @Transactional
    public CommentResponseDto updateComment(Long id, UpdateCommentRequestDto updateCommentRequestDto) {

        String content = updateCommentRequestDto.getContent();

        Comment comment = commentRepository.findById(id).orElseThrow();
        comment.updateContent(content);

        commentRepository.save(comment);

        return CommentResponseDto.fromEntity(comment);
    }

    @Override
    @Transactional
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

//    // 유저 유무 확인
//    private boolean userIsExist(Long userId) {
//        return userService.findById(id).isPresent();
//    }
//
//    // 게시글 유무 확인
//    private boolean postIsExist(Long postId) {
//        return postService.find(postId) != null;
//    }
}