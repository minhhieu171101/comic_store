package com.example.comic_store.service;

import com.example.comic_store.entity.User;
import com.example.comic_store.request.CreateUserRequest;

public interface UserService {
    void register(CreateUserRequest request);


    User getUserByUsername(String username);

    User updateUser(UpdateProfileRequest request);

    void changePassword(ChangePasswordRequest request);
}
