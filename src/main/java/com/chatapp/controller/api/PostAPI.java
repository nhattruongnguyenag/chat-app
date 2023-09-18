package com.chatapp.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.chatapp.commond.ResponseData;
import com.chatapp.dto.response.PostInfoResponeDTO;
import com.chatapp.service.PostService;

@RestController
@RequestMapping("/api")
public class PostAPI {
    @Autowired
    PostService postService;

    @GetMapping({"posts", "posts/"})
    public ResponseData<List<PostInfoResponeDTO>> findAll() {
        ResponseData<List<PostInfoResponeDTO>> responseData = new ResponseData<>(HttpStatus.OK,"success",postService.findAll());
        return responseData;
    }
}
