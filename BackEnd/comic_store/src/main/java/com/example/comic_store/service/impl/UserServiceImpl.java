package com.example.comic_store.service.impl;

import com.example.comic_store.dto.PurchaseOrderDTO;
import com.example.comic_store.dto.RegisterDTO;
import com.example.comic_store.dto.UserDTO;
import com.example.comic_store.entity.Role;
import com.example.comic_store.entity.UserEntity;
import com.example.comic_store.entity.UserRole;
import com.example.comic_store.repository.RoleRepository;
import com.example.comic_store.repository.UserRepository;
import com.example.comic_store.repository.UserRoleRepository;
import com.example.comic_store.service.UserService;
import java.time.LocalDateTime;
import java.util.Random;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @Autowired
    private ModelMapper modelMapper;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    public String register(RegisterDTO registerDTO, String code) {
        if (!registerDTO.getCode().equals(code)) {
            return "code is wrong!";
        }
        UserEntity user = modelMapper.map(registerDTO, UserEntity.class);
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        UserEntity userSave = userRepository.save(user);

        Role role = roleRepository.findByRoleName("USER");

        UserRole userRole = new UserRole();
        userRole.setUserId(userSave.getId());
        userRole.setRoleId(role.getId());

        userRoleRepository.save(userRole);
        return "register successfully!";
    }


    /**
     * sinh ra code cho việc xác thực eaml
     *
     * @return String là mã code sau khi random 6 chữ số
     * @modifiedBy
     * @modifiedDate
     * @vesion 1.0
     */
    @Override
    public String generateCode() {
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        for (int num = 0; num < 6; num ++) {
            code.append(random.nextInt(0, 10));
        }
        return code.toString();
    }

    @Override
    public UserDTO getUserInfo(String username) {
        UserEntity user = userRepository.findByUsername(username).orElse(null);
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public Page<UserDTO> getUserPage(UserDTO userDTO) {
        Pageable pageable = PageRequest.of(userDTO.getPage(),
                userDTO.getPageSize(),
                Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<UserEntity> userEntityPage = userRepository.findAll(pageable);
        return userEntityPage.map(element -> modelMapper.map(element, UserDTO.class));
    }
}
