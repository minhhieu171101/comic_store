package com.example.comic_store.service.impl;

import com.example.comic_store.dto.RegisterDTO;
import com.example.comic_store.entity.Role;
import com.example.comic_store.entity.UserEntity;
import com.example.comic_store.entity.UserRole;
import com.example.comic_store.repository.RoleRepository;
import com.example.comic_store.repository.UserRepository;
import com.example.comic_store.repository.UserRoleRepository;
import com.example.comic_store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public String register(RegisterDTO registerDTO) {
        UserEntity user = new UserEntity();
        user.setUsername(registerDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        UserEntity userSave = userRepository.save(user);

        Role role = roleRepository.findByRoleName("USER");

        UserRole userRole = new UserRole();
        userRole.setUserId(userSave.getId());
        userRole.setRoleId(role.getId());

        userRoleRepository.save(userRole);
        return "register successfully!";
    }
}
