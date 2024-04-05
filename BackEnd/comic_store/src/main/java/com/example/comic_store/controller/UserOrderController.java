package com.example.comic_store.controller;

import com.example.comic_store.dto.ServiceResult;
import com.example.comic_store.dto.UserOrderDTO;
import com.example.comic_store.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user-order")
public class UserOrderController {

    @Autowired
    private UserOrderService userOrderService;

    @PostMapping("/order")
    public ResponseEntity<ServiceResult<String>> saveOrder(@RequestBody UserOrderDTO userOrderDTO) {
        ServiceResult<String> result = userOrderService.saveUserOrder(userOrderDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
