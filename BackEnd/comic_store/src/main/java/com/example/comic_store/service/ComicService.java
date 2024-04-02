package com.example.comic_store.service;

import com.example.comic_store.dto.ComicDTO;
import java.util.List;

public interface ComicService {

    List<ComicDTO> getListComicLanding(int page, int pageSize);
}
