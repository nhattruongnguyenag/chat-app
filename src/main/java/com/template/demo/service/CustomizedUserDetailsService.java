package com.template.demo.service;

import com.template.demo.dto.CustomizedUserDTO;
import com.template.demo.entity.UserEntity;
import com.template.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomizedUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findOneByUserNameAndStatus(username, 1);
        if (userEntity == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return createCustomizedUser(userEntity);
    }

    private User createCustomizedUser(UserEntity userEntity) {
        List<GrantedAuthority> authorities = getGrantedAuthorities(userEntity);
        User myUserDetail
                = new CustomizedUserDTO.Builder(userEntity.getUserName(), userEntity.getPassword(), authorities)
                .setFullName(userEntity.getFullName())
                .setEnabled(true)
                .setAccountNonExpired(true)
                .setAccountNonLocked(true)
                .setCredentialsNonExpired(true)
                .build();
        return myUserDetail;
    }

    private static List<GrantedAuthority> getGrantedAuthorities(UserEntity userEntity) {
        return userEntity.getRoles()
                .stream()
                .map(authority -> new SimpleGrantedAuthority("ROLE_" + authority.getCode()))
                .collect(Collectors.toList());
    }
}
