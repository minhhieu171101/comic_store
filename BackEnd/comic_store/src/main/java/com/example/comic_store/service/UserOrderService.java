package com.example.comic_store.service;

import com.example.comic_store.dto.ServiceResult;
import com.example.comic_store.dto.UserOrderDTO;

public interface UserOrderService {
    ServiceResult<String> saveUserOrder(UserOrderDTO userOrderDTO);
}
