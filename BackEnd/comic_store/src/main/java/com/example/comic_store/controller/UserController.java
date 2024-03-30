package com.example.comic_store.controller;

import com.example.comic_store.constants.LoginConstants;
import com.example.comic_store.dto.AuthResponseDTO;
import com.example.comic_store.dto.LoginDTO;
import com.example.comic_store.dto.MailDTO;
import com.example.comic_store.dto.RegisterDTO;
import com.example.comic_store.repository.UserRepository;
import com.example.comic_store.security.jwt.JwtUtils;
import com.example.comic_store.service.MailService;
import com.example.comic_store.service.UserService;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtGenerator;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private MailService mailService;

    private String tempGeneratedCode;

    private LocalDateTime codeGeneratedTime;

    /**
     * Đăng nhập tài khoản hệ thống
     *
     * @param loginDTO Chứa thông tin username, password để đăng nhập
     * @return ResponseEntity<String> Chứa thông tin message
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
     * @vesion 1.0
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO) {
        try {
            if (Math.abs(ChronoUnit.SECONDS.between(LocalDateTime.now(), this.codeGeneratedTime)) > 60) {
                return new ResponseEntity<>("time is out of 60", HttpStatus.BAD_REQUEST);
            }
            String message = userService.register(registerDTO, tempGeneratedCode);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("err");
            return new ResponseEntity<>("err", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Gửi email xác thực
     *
     * @param mailDTO chứa thông tin về mail người đăng ký
     * @return ResponseEntity<String> trả về tin nhắn
     * @modifiedBy
     * @modifiedDate
     * @vesion 1.0
     */
    @PostMapping("/send")
    public ResponseEntity<String> sendMailAuthorization(@RequestBody MailDTO mailDTO) {
        this.tempGeneratedCode = userService.generateCode();
        this.codeGeneratedTime = LocalDateTime.now();

        mailDTO.setSubject(LoginConstants.SUBJECT_VALID);
        String messageBody = LoginConstants.INTRO_MAIL
                + "\n"
                + this.tempGeneratedCode
                + "\n "
                + LoginConstants.END_MAIL;
        mailDTO.setMessage(messageBody);
        mailService.sendMail(mailDTO);
        return new ResponseEntity<>("Successfully send mail", HttpStatus.OK);
    }
}
