package com.example.comic_store.service;

import com.example.comic_store.dto.ComicDTO;
import com.example.comic_store.dto.ComicDetailDTO;
import java.util.List;
import org.springframework.data.domain.Page;

public interface ComicService {

    List<ComicDTO> getListComicLanding(int page, int pageSize);
    ComicDetailDTO getComicBy(Long id);
    Page<ComicDTO> getPageComic(int page, int pageSize, Long typeComicId);
}
