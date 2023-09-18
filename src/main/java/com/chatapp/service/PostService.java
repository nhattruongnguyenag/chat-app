package com.chatapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.chatapp.dto.response.PostInfoResponeDTO;

import jakarta.transaction.Transactional;

@Service
@Transactional
public interface PostService {
    List<PostInfoResponeDTO> findAll();
}
