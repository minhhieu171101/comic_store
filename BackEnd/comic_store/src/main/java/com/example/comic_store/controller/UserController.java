package com.example.comic_store.controller;


import com.example.comic_store.dto.AuthResponseDTO;
import com.example.comic_store.dto.LoginDTO;
import com.example.comic_store.dto.RegisterDTO;
import com.example.comic_store.repository.UserRepository;
import com.example.comic_store.security.jwt.JwtGenerator;
import com.example.comic_store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtGenerator jwtGenerator;

    /**
     * Đăng nhập tài khoản hệ thống
     *
     * @param loginDTO Chứa thông tin username, password để đăng nhập
     * @return ResponseEntity<String> Chứa thông tin message
     * @author Nguyen Minh Hieu
     * @vesion 1.0
     */
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(),
                loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
    }



    /**
     * Đăng ký tài khoản hệ thống
     *
     * @param registerDTO Chứa thông tin cần thiết để đăng ký
     * @return ResponseEntity<String> Chứa thông tin message trả lại
     * @author Nguyen Minh Hieu
     * @vesion 1.0
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO) {
        if (userRepository.existsByUsername(registerDTO.getUsername())) {
            return new ResponseEntity<>("username is taken", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userService.register(registerDTO), HttpStatus.OK);
    }
}
