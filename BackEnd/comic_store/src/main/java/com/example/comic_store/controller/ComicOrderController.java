package com.example.comic_store.controller;

import com.example.comic_store.dto.ComicOrderDTO;
import com.example.comic_store.dto.UserDTO;
import com.example.comic_store.service.ComicOrderService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comic-order")
public class ComicOrderController {

    @Autowired
    private ComicOrderService comicOrderService;

    @PostMapping("/order-list")
    public ResponseEntity<List<ComicOrderDTO>> getListComicOrder(@RequestBody UserDTO userDTO) {
        List<ComicOrderDTO> comicOrderDTOList = comicOrderService.getListComicOrder(userDTO.getUsername());
        return  new ResponseEntity<>(comicOrderDTOList, HttpStatus.OK);
    }
}
