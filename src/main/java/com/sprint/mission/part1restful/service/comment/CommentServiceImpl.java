package com.sprint.mission.part1restful.service.comment;

import com.sprint.mission.part1restful.domain.Comment;
import com.sprint.mission.part1restful.dto.comment.CommentResponseDto;
import com.sprint.mission.part1restful.dto.comment.CreateCommentRequestDto;
import com.sprint.mission.part1restful.dto.comment.UpdateCommentRequestDto;
import com.sprint.mission.part1restful.exception.comment.CommentNotFoundException;
import com.sprint.mission.part1restful.exception.post.PostNotFoundException;
import com.sprint.mission.part1restful.exception.user.UserNotFoundException;
import com.sprint.mission.part1restful.repository.CommentRepository;
import com.sprint.mission.part1restful.service.PostService;
import com.sprint.mission.part1restful.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final PostService postService;

    @Override
    @Transactional
    public CommentResponseDto createComment(CreateCommentRequestDto createCommentRequestDto) {

        Long userId = createCommentRequestDto.getUserId();
        Long postId = createCommentRequestDto.getPostId();
        String content = createCommentRequestDto.getContent();

        userIsExist(userId);
        postIsExist(postId);

        Comment comment = new Comment(userId, postId, content);

        commentRepository.save(comment);

        return CommentResponseDto.fromEntity(comment);
    }

    @Override
    public CommentResponseDto findById(Long id) {

        Comment comment = commentRepository.findById(id).orElseThrow(() -> new CommentNotFoundException("comment not found with id: " + id));

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

        commentIsExist(id);

        String content = updateCommentRequestDto.getContent();

        Comment comment = commentRepository.findById(id).orElseThrow();
        comment.updateContent(content);

        commentRepository.save(comment);

        return CommentResponseDto.fromEntity(comment);
    }

    @Override
    @Transactional
    public void deleteComment(Long id) {
        commentIsExist(id);
        commentRepository.deleteById(id);
    }

    // 유저 유무 확인 - 없을 경우 예외
    private void userIsExist(Long userId) {
        if (userService.getUserById(userId).isEmpty()) {
            throw new UserNotFoundException("user not found with id: " + userId);
        };
    }

    // 게시글 유무 확인 - 없을 경우 예외 -> 현재 작동안함
    private void postIsExist(Long postId) {
        if (postService.find(postId) == null) {
            throw new PostNotFoundException("post not found with id: " + postId);
        }
    }

    // 댓글 유무 확인 - 없을 경우 예외
    private void commentIsExist(Long id) {
        if (findById(id) == null) {
            throw new CommentNotFoundException("comment not found with id: " + id);
        }
    }
}