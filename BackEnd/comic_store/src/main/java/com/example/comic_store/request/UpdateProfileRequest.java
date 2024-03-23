package com.example.comic_store.request;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProfileRequest {
    private Long id;

    private String username;

    private String fullname;

    private String password;

    private Date birthday;

    private String address;

    private String phone;

    private String gmail;

    private String imgUser;
}
