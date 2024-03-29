package com.example.comic_store.dto;

import java.util.Date;
import lombok.Data;

@Data
public class RegisterDTO {
    private String username;
    private String fullName;
    private String password;
    private String gmail;
    private Date birthDay;
    private String phone;
}
