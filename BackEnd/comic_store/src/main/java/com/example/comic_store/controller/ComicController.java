package com.example.comic_store.controller;

import com.example.comic_store.dto.ComicDTO;
import com.example.comic_store.service.ComicService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/comic")
public class ComicController {

    @Autowired
    private ComicService comicService;

    @GetMapping("/list-comic/{page}/{pageSize}")
    public ResponseEntity<List<ComicDTO>> getListComicDTO(@PathVariable("page") int page,
                                                          @PathVariable("pageSize") int pageSize) {
        try {
            List<ComicDTO> comicDTOList = comicService.getListComicLanding(page, pageSize);
            return new ResponseEntity<>(comicDTOList, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }
}
