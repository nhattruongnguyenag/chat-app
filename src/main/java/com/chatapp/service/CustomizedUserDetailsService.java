package com.chatapp.service;

import com.chatapp.model.CustomizedUserModel;
import com.chatapp.entity.UserEntity;
import com.chatapp.repository.UserRepository;
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

@Service(value = "userCustomService")
public class CustomizedUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findOneByUsername(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return createCustomizedUser(userEntity);
    }

    private User createCustomizedUser(UserEntity userEntity) {
        List<GrantedAuthority> authorities = getGrantedAuthorities(userEntity);
        User myUserDetail
                = new CustomizedUserModel.Builder(userEntity.getUsername(), userEntity.getPassword(), authorities)
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
