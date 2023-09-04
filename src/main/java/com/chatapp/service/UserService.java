package com.chatapp.service;

import com.chatapp.dto.AuthTokenDTO;
import com.chatapp.dto.UserDTO;
import com.chatapp.dto.request.UserDTORequest;

import java.util.List;

public interface UserService {
    List<UserDTO> findAll();
    UserDTO findByUsernameAndPassword(String username, String password);
    UserDTO saveOrUpdate(UserDTO userDTO);
    UserDTO delete(Long userId);
    UserDTO changeStatus(Long userId, Byte status);

    AuthTokenDTO login(UserDTORequest userDTORequest);
}
