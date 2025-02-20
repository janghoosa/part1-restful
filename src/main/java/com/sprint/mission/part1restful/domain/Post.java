package com.sprint.mission.part1restful.domain;


import com.sprint.mission.part1restful.dto.PostCreateDto;
import com.sprint.mission.part1restful.dto.PostUpdateDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id; //Post id

    @Column(nullable = false)
    private String title; //게시글 제목

    @Column(nullable = false)
    private String content; //게시글 내용

    @Column(nullable = false)
    private Long authorId; //작성자 id


    public Post(PostCreateDto postCreateDto){
        this.title=postCreateDto.title();
        this.content=postCreateDto.content();
        this.authorId = postCreateDto.authorId();
    }

    public void update(PostUpdateDto postUpdateDto){
        this.title=postUpdateDto.title();
        this.content=postUpdateDto.content();
    }

}
