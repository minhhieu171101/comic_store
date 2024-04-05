package com.example.comic_store.service;

import com.example.comic_store.dto.ComicOrderDTO;
import java.util.List;

public interface ComicOrderService {
    List<ComicOrderDTO> getListComicOrder(String username);
}
