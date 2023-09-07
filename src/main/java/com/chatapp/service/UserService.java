package com.chatapp.service;

import com.chatapp.dto.AuthTokenDTO;
import com.chatapp.dto.UserDTO;
import com.chatapp.dto.request.UserLoginRequestDTO;
import com.chatapp.dto.response.UserInfoResponseDTO;

import java.util.List;

public interface UserService {
    List<UserInfoResponseDTO> findAll();
    UserInfoResponseDTO findByUsernameAndPassword(String username, String password);
    UserInfoResponseDTO saveOrUpdate(UserDTO userDTO);
    UserInfoResponseDTO delete(Long userId);
    UserInfoResponseDTO changeStatus(Long userId, Byte status);
    AuthTokenDTO login(UserLoginRequestDTO userDTORequest);
    UserInfoResponseDTO getUserFromToken(String token);
    UserInfoResponseDTO getUserByUsername(String username);
}
