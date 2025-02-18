package com.sprint.mission.part1restful.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostPageResponse{
    List<PostRequestDto> content;
    int pageNo;
    int pageSize;
    long totalElements;
    int totalPages;
    boolean last;

}
