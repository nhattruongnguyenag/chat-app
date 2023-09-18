package com.chatapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatapp.converter.response.PostInfoResponeConverter;
import com.chatapp.dto.response.PostInfoResponeDTO;
import com.chatapp.repository.PostRepository;
import com.chatapp.service.PostService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PostServiceImpl implements PostService{

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostInfoResponeConverter postInfoResponeConverter;

    @Override
    public List<PostInfoResponeDTO> findAll() {
        return postInfoResponeConverter.toDTOGroup(postRepository.findAll());
    }
    
}
