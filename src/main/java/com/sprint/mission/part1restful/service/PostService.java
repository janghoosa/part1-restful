package com.sprint.mission.part1restful.service;

import com.sprint.mission.part1restful.domain.Post;
import com.sprint.mission.part1restful.dto.PostCreateDto;
import com.sprint.mission.part1restful.dto.PostPageResponse;
import com.sprint.mission.part1restful.dto.PostResponseDto;
import com.sprint.mission.part1restful.dto.PostUpdateDto;

import java.util.List;

public interface PostService {

    public PostResponseDto create(PostCreateDto postCreateDto);

    public PostResponseDto find(Long id);

    public List<PostResponseDto> findAll() ;

    public void update(Long id, PostUpdateDto postUpdateDto) ;

    public void delete(Long id);

    public PostPageResponse searchAllPaging(int pageNo, int pageSize, String sortBy) ;
}
