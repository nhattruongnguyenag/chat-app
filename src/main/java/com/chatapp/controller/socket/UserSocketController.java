package com.chatapp.controller.socket;

import com.chatapp.dto.UserDTO;
import com.chatapp.dto.response.UserInfoResponseDTO;
import com.chatapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserSocketController {
    @Autowired
    private UserService userService;

    @MessageMapping({"/users/status", "/users/status/"})
    @SendTo({"/topic/users", "/topic/users/"})
    public List<UserInfoResponseDTO> changeUserStatus(UserDTO userDTO) {
        userService.changeStatus(userDTO.getId(), userDTO.getStatus());
        return userService.findAll();
    }

    @MessageMapping({"/users/listen", "/users/listen/"})
    @SendTo({"/topic/users", "/topic/users/"})
    public List<UserInfoResponseDTO> getUserList() {
        return userService.findAll();
    }
}
