package com.sprint.mission.part1restful.apidocs;

import com.sprint.mission.part1restful.dto.PostCreateDto;
import com.sprint.mission.part1restful.dto.PostPageResponse;
import com.sprint.mission.part1restful.dto.PostResponseDto;
import com.sprint.mission.part1restful.dto.PostUpdateDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Post API", description = "Post management operations")
public interface PostApiDocs {

    @Operation( summary = "Create a new post", description = "새로운 포스트를 작성한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Post created"),
            @ApiResponse(responseCode = "400", description = "Invalid request data")

    })
    ResponseEntity<PostResponseDto> createPost(PostCreateDto postCreateDto);

    @Operation( summary = "Read a post", description = "포스트 하나를 id를 통해 읽어 들인다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Post read"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    ResponseEntity<PostResponseDto> getPost (Long id);


    @Operation( summary = "Read posts", description = "포스트를 페이징으로 읽는다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Posts read"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    ResponseEntity<PostPageResponse> getAllPosts(int pageNo, int pageSize, String sortBy);

    @Operation( summary = "Update a post", description = "포스트를 업데이트한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Post updated"),
            @ApiResponse(responseCode = "400", description = "Invalid request data")
    })
    ResponseEntity<String> updatePost(Long id, PostUpdateDto postUpdateDto);

    @Operation( summary = "Delete a post", description = "포스트를 삭제한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Post deleted"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    ResponseEntity<String> deletePost(Long id);

}
