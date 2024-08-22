package com.flow.main.controller;

import com.flow.main.dto.controller.post.delete.request.PostDeleteRequestDto;
import com.flow.main.dto.controller.post.delete.response.PostDeleteResponseDto;
import com.flow.main.dto.controller.post.get.response.PostGetResponseDto;
import com.flow.main.dto.controller.post.keyword.response.FindByKeywordResponseDto;
import com.flow.main.dto.controller.post.latest.response.PostLatestResponseDto;
import com.flow.main.dto.controller.post.modify.request.PostModifyRequestDto;
import com.flow.main.dto.controller.post.modify.response.PostModifyResponseDto;
import com.flow.main.dto.controller.post.tranding.response.GetTrandingPostsResponseDto;
import com.flow.main.dto.controller.post.write.request.PostWriteRequestDto;
import com.flow.main.dto.controller.post.write.response.PostWriteResponseDto;
import com.flow.main.dto.jpa.posts.PostsDto;
import com.flow.main.service.posts.PostsFindByKeywordService;
import com.flow.main.service.posts.PostsGetTrandingService;
import com.flow.main.service.posts.PostsLatestService;
import com.flow.main.service.posts.PostsDeleteService;
import com.flow.main.service.posts.PostsGetService;
import com.flow.main.service.posts.PostsModifyService;
import com.flow.main.service.posts.PostsWriteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostsLatestService postsLatestService;
    private final PostsGetService postsGetService;
    private final PostsGetTrandingService postsGetTrandingService;
    private final PostsFindByKeywordService postsFindByKeywordService;
    private final PostsWriteService postsWriteService;
    private final PostsModifyService postsModifyService;
    private final PostsDeleteService postsDeleteService;

    @GetMapping("/latest")
    public ResponseEntity<PostLatestResponseDto> getLatest(){
        PostsDto postsDto = postsLatestService.getLatest();
        return ResponseEntity.ok(PostLatestResponseDto.builder()
                .postId(postsDto.getPostId())
                .build());
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostGetResponseDto> get(@PathVariable("postId") Long postId){
        return ResponseEntity.ok(postsGetService.get(postId));
    }

    @GetMapping("/tranding/{page}/{count}")
    public ResponseEntity<GetTrandingPostsResponseDto> getTrandingPosts(@PathVariable("page") Long page, @PathVariable("count") Long count){
        log.info("page : {}", page);
        log.info("count : {}", count);
        return ResponseEntity.ok(postsGetTrandingService.getTrandingPosts(page, count));
    }

    @GetMapping("/search/{keyword}/{page}/{count}")
    public ResponseEntity<FindByKeywordResponseDto> findPostsByKeyword(@PathVariable("keyword") String keyword, @PathVariable("page") Long page, @PathVariable("count") Long count, @RequestParam(name = "email", required = false) String email){
        log.info("keyword : {}", keyword);
        log.info("page : {}", page);
        log.info("count : {}", count);
        log.info("email : {}", email);
        return ResponseEntity.ok(postsFindByKeywordService.findPostsByKeyword(keyword, page, count, email));
    }

    @PostMapping("")
    public ResponseEntity<PostWriteResponseDto> write(@RequestBody final PostWriteRequestDto postWriteRequestDto){
        log.info("email : {}", postWriteRequestDto.getEmail());
        log.info("category : {}", postWriteRequestDto.getCategory());
        postWriteRequestDto.getTags().forEach(tagsNameDto -> { log.info("tagName : {}", tagsNameDto.getTagName());});
        log.info("title : {}", postWriteRequestDto.getTitle());
        log.info("content : {}", postWriteRequestDto.getContent());

        return ResponseEntity.ok(postsWriteService.write(postWriteRequestDto));
    }

    @PatchMapping("/{postId}")
    public ResponseEntity<PostModifyResponseDto> modify(@PathVariable("postId") Long postId, @RequestBody final PostModifyRequestDto postModifyRequestDto){
        log.info("postId : {}", postId);
        log.info("email : {}", postModifyRequestDto.getEmail());
        log.info("category : {}", postModifyRequestDto.getCategory());
        postModifyRequestDto.getTags().forEach(tagsNameDto -> { log.info("tagName : {}", tagsNameDto.getTagName());});
        log.info("title : {}", postModifyRequestDto.getTitle());
        log.info("content : {}", postModifyRequestDto.getContent());

        return ResponseEntity.ok(postsModifyService.modify(postId, postModifyRequestDto));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<PostDeleteResponseDto> delete(@PathVariable("postId") Long postId, @RequestBody final
        PostDeleteRequestDto postDeleteRequestDto){
        log.info("postId : {}", postId);
        log.info("email : {}", postDeleteRequestDto.getEmail());

        return ResponseEntity.ok(postsDeleteService.delete(postId, postDeleteRequestDto));
    }
}
