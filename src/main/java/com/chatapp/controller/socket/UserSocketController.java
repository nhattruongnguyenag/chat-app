package com.chatapp.controller.socket;

import com.chatapp.dto.UserDTO;
import com.chatapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserSocketController {
    @Autowired
    private UserService userService;

    @MessageMapping("/users/status")
    @SendTo("/topics/users")
    public List<UserDTO> changeUserStatus(UserDTO userDTO) {
        userService.changeStatus(userDTO.getId(), userDTO.getStatus());
        return userService.findAll();
    }

    @MessageMapping({"users/listen",
            "users/listen/"})
    @SendTo({"topics/users",
            "topics/users/"})
    public List<UserDTO> getUserList() {
        return userService.findAll();
    }
}
