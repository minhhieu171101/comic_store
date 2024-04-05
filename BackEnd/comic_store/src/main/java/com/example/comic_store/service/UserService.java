package com.example.comic_store.service;

import com.example.comic_store.dto.RegisterDTO;
import com.example.comic_store.dto.UserDTO;
import org.springframework.data.domain.Page;

public interface UserService {
    String register(RegisterDTO registerDTO, String code);
    String generateCode();
    UserDTO getUserInfo(String username);
    Page<UserDTO> getUserPage(UserDTO userDTO);
}
