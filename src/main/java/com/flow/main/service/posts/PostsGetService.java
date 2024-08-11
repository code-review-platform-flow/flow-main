package com.flow.main.service.posts;

import com.flow.main.dto.controller.post.TagsNameDto;
import com.flow.main.dto.controller.post.get.response.PostGetResponseDto;
import com.flow.main.dto.jpa.categories.CategoriesDto;
import com.flow.main.dto.jpa.major.MajorDto;
import com.flow.main.dto.jpa.posts.PostsDto;
import com.flow.main.dto.jpa.posttags.PostTagsDto;
import com.flow.main.dto.jpa.tags.TagsDto;
import com.flow.main.dto.jpa.userinfo.UserInfoDto;
import com.flow.main.dto.jpa.users.UsersDto;
import com.flow.main.service.categories.persistence.CategoriesService;
import com.flow.main.service.major.persistence.MajorService;
import com.flow.main.service.posts.persistence.PostsService;
import com.flow.main.service.posttags.persistence.PostTagsService;
import com.flow.main.service.tags.persistence.TagsService;
import com.flow.main.service.userinfo.persistence.UserInfoService;
import com.flow.main.service.users.persistence.UsersService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostsGetService {

    private final UsersService usersService;
    private final UserInfoService userInfoService;
    private final MajorService majorService;
    private final PostsService postsService;
    private final CategoriesService categoriesService;
    private final PostTagsService postTagsService;
    private final TagsService tagsService;

    public PostGetResponseDto get(Long postId){
        PostsDto postsDto = postsService.findByPostId(postId);
        UsersDto usersDto = usersService.findByUserId(postsDto.getUserId());
        UserInfoDto userInfoDto = userInfoService.findByUserId(usersDto.getUserId());
        System.out.println(userInfoDto.getUserName());
        System.out.println(userInfoDto.getMajorId());
        System.out.println(userInfoDto.getSchoolId());
        System.out.println(userInfoDto.getStudentNumber());
        MajorDto majorDto = majorService.findByMajorId(userInfoDto.getMajorId());
        CategoriesDto categoriesDto = categoriesService.findByCategoryId(postsDto.getCategoryId());
        List<PostTagsDto> postTagsDtos = postTagsService.findAllByPostId(postsDto.getPostId());

        List<TagsNameDto> tagsNameDtos = new ArrayList<>();
        for (PostTagsDto p : postTagsDtos){
            TagsDto tagsDto = tagsService.findByTagId(p.getTagId());
            TagsNameDto tagsNameDto = TagsNameDto.builder()
                .tagName(tagsDto.getTagName())
                .build();
            tagsNameDtos.add(tagsNameDto);
        }

        return PostGetResponseDto.builder()
            .postId(postsDto.getPostId())
            .userName(userInfoDto.getUserName())
            .majorName(majorDto.getMajorName())
            .studentNumber(userInfoDto.getStudentNumber())
            .title(postsDto.getTitle())
            .content(postsDto.getContent())
            .categoryName(categoriesDto.getCategoryName())
            .tags(tagsNameDtos)
            .profileUrl(userInfoDto.getProfileUrl())
            .createDate(postsDto.getCreateDate())
            .build();
    }

}
