package com.sprint.mission.part1restful.domain;


import com.sprint.mission.part1restful.dto.PostCreateDto;
import com.sprint.mission.part1restful.dto.PostUpdateDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Entity
@Getter
public class Post {

    @Id
    private  UUID id;

    private String title;
    private String content;

    private  UUID authorId;


    protected Post() {}

    public Post(PostCreateDto postCreateDto){
        this.id = UUID.randomUUID();
        this.title=postCreateDto.title();
        this.content=postCreateDto.content();
        this.authorId = postCreateDto.authorId();
    }

    public void update(PostUpdateDto postUpdateDto){
        this.title=postUpdateDto.title();
        this.content=postUpdateDto.content();
    }

}
