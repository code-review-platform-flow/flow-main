package com.flow.main.service.posts;

import com.flow.main.dto.controller.post.write.request.PostWriteRequestDto;
import com.flow.main.dto.jpa.categories.CategoriesDto;
import com.flow.main.dto.jpa.posts.PostsDto;
import com.flow.main.dto.jpa.users.UsersDto;
import com.flow.main.service.categories.persistence.CategoriesService;
import com.flow.main.service.posts.persistence.PostsService;
import com.flow.main.service.posttags.PostTagsSaveAllService;
import com.flow.main.service.users.persistence.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostsWriteService {

    private final UsersService usersService;
    private final CategoriesService categoriesService;
    private final PostsService postsService;
    private final PostTagsSaveAllService postTagsSaveAllService;

    public PostsDto write(PostWriteRequestDto postWriteRequestDto){
        UsersDto usersDto = usersService.findByEmail(postWriteRequestDto.getEmail());
        CategoriesDto categoriesDto = categoriesService.findByCategoryName(postWriteRequestDto.getCategory());

        PostsDto postsDto = PostsDto.builder()
            .userId(usersDto.getUserId())
            .categoryId(categoriesDto.getCategoryId())
            .title(postWriteRequestDto.getTitle())
            .content(postWriteRequestDto.getContent())
            .build();
        PostsDto savedDto = postsService.save(postsDto);

        postTagsSaveAllService.saveAll(savedDto.getPostId(), postWriteRequestDto.getTags());

        return savedDto;
    }
}
