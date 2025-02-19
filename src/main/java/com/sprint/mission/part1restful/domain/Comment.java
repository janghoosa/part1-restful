package com.sprint.mission.part1restful.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Getter
@NoArgsConstructor  // 기본 생성자 없으면 JPA 에러 발생
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;            // pk

    @Column(name = "createAt")
    Instant createAt;   // 작성 날짜

    @Column(name = "updateAt")
    Instant updateAt;   // 수정 날짜

    @Column(name = "userId", nullable = false)
    Long userId;        // 작성자 pk

    @Column(name = "postId", nullable = false)
    Long postId;        // 작성 게시글 pk

    @Column(name = "content", nullable = false)
    String content;     // 댓글 내용

    // 업데이트
    public void updateContent(String content) {
        this.content = content.trim();
        updateUpdateAt();
    }

    public void updateUpdateAt() {
        this.updateAt = Instant.now();
    }

    // 생성자
    public Comment(Long userId, Long postId, String content) {
        this.createAt = Instant.now();

        this.userId = userId;
        this.postId = postId;
        this.content = content.trim();
    }
}
