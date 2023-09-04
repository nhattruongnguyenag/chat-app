package com.chatapp.controller.api;

import com.chatapp.converter.UserConverter;
import com.chatapp.dto.AuthTokenDTO;
import com.chatapp.dto.UserDTO;
import com.chatapp.dto.request.UserDTORequest;
import com.chatapp.model.ErrorModel;
import com.chatapp.repository.UserRepository;
import com.chatapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserAPI {
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private UserService userService;

    @GetMapping({"users", "users/"})
    public List<UserDTO> findAll() {
        return userService.findAll();
    }

    @PostMapping({"login", "login/"})
    ResponseEntity<AuthTokenDTO> login(@RequestBody UserDTORequest userDTORequest) {
        return ResponseEntity.ok(userService.login(userDTORequest));
    }

    @PostMapping(value = {"/users", "/users/"})
    public UserDTO save(@RequestBody UserDTO userDTO) {
        return userService.saveOrUpdate(userDTO);
    }

    @DeleteMapping({"/users/{id}", "/users/{id}/"})
    public ResponseEntity delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }
}
