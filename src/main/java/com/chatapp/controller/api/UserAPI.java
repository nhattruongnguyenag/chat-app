package com.chatapp.controller.api;

import com.chatapp.converter.request.UserRequestConverter;
import com.chatapp.dto.AuthTokenDTO;
import com.chatapp.dto.UserDTO;
import com.chatapp.dto.request.UserLoginRequestDTO;
import com.chatapp.dto.response.UserInfoResponseDTO;
import com.chatapp.service.UserService;
import com.chatapp.util.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserAPI {
    @Autowired
    private UserRequestConverter userConverter;
    @Autowired
    private UserService userService;
    @Autowired
    private TokenProvider tokenProvider;

    @GetMapping({"users", "users/"})
    public List<UserInfoResponseDTO> findAll() {
        return userService.findAll();
    }

    @GetMapping({"users/token/{token}", "users/token/{token}/"})
    public UserInfoResponseDTO getUserFromToken(@PathVariable("token") String token) {
        return userService.getUserFromToken(token);
    }

    @GetMapping({"users/{username}", "users/{username}/"})
    public UserInfoResponseDTO getUserByUsername(@PathVariable("username") String username) {
        return userService.getUserByUsername(username);
    }

    @PostMapping({"login", "login/"})
    ResponseEntity<AuthTokenDTO> login(@RequestBody UserLoginRequestDTO userDTORequest) {
        return ResponseEntity.ok(userService.login(userDTORequest));
    }

    @PostMapping({"users", "users/"})
    public AuthTokenDTO save(@RequestBody UserDTO userDTO) {
        UserInfoResponseDTO tempUserDTO = userService.saveOrUpdate(userDTO);
        String token = tokenProvider.generateToken(tempUserDTO.getUsername());
        return new AuthTokenDTO(token);
    }

    @DeleteMapping({"users/{id}", "users/{id}/"})
    public ResponseEntity delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }
}
