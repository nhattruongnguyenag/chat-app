package com.chatapp.service.impl;

import com.chatapp.converter.request.UserRequestConverter;
import com.chatapp.converter.response.UserInfoResponseConverter;
import com.chatapp.dto.AuthTokenDTO;
import com.chatapp.dto.UserDTO;
import com.chatapp.dto.request.UserLoginRequestDTO;
import com.chatapp.dto.response.UserInfoResponseDTO;
import com.chatapp.entity.UserEntity;
import com.chatapp.exception.DuplicateUsernameException;
import com.chatapp.repository.UserRepository;
import com.chatapp.service.UserService;
import com.chatapp.util.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    TokenProvider tokenProvider;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserInfoResponseConverter userInfoResponseConverter;
    @Autowired
    private UserRequestConverter userConverter;

    @Override
    public List<UserInfoResponseDTO> findAll() {
        return userInfoResponseConverter.toDTOGroup(userRepository.findAllByStatusNot((byte) -1));
    }

    @Override
    public UserInfoResponseDTO findByUsernameAndPassword(String username, String password) {
        return userInfoResponseConverter.toDTO(userRepository.findOneByUsernameAndPassword(username, password));
    }

    @Override
    public UserInfoResponseDTO saveOrUpdate(UserDTO userDTO) {
        UserEntity userEntity;
        if (userDTO.getId() != null) {
            if (userRepository.findOneById(userDTO.getId()) == null) {
                throw new RuntimeException("user_does_not_exists");
            }
        } else {
            if (userRepository.findOneByUsername(userDTO.getUsername()) != null) {
                throw new DuplicateUsernameException("user_already_exists");
            }

            userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            userDTO.setStatus((byte) 0);
        }

        userEntity = userConverter.toEntity(userDTO);

        return userInfoResponseConverter.toDTO(userRepository.save(userEntity));
    }

    @Override
    public UserInfoResponseDTO changeStatus(Long userId, Byte status) {
        UserEntity userEntity = userRepository.findOneById(userId);
        if (userEntity == null) {
            throw new RuntimeException("user_does_not_exists");
        }
        userEntity.setStatus(status);

        return userInfoResponseConverter.toDTO(userRepository.save(userEntity));
    }

    @Override
    public UserInfoResponseDTO delete(Long userId) {
        UserEntity userEntity = userRepository.findOneById(userId);

        if (userEntity == null) {
            throw new RuntimeException("user_does_not_exits");
        }

        userEntity.setStatus((byte) 1);
        return userInfoResponseConverter.toDTO(userRepository.save(userEntity));
    }

    @Override
    public AuthTokenDTO login(UserLoginRequestDTO userLoginRequest) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLoginRequest.getUsername(),
                        userLoginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = tokenProvider.generateToken(authentication.getName());
        return new AuthTokenDTO(token);
    }

    @Override
    public UserInfoResponseDTO getUserFromToken(String token) {
        String username = tokenProvider.extractUsernameFromToken(token);
        UserEntity userEntity = userRepository.findOneByUsername(username);
        return userInfoResponseConverter.toDTO(userEntity);
    }

    @Override
    public UserInfoResponseDTO getUserByUsername(String username) {
        return userInfoResponseConverter.toDTO(userRepository.findOneByUsername(username));
    }
}
