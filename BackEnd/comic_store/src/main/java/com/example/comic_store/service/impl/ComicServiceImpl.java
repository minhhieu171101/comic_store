package com.example.comic_store.service.impl;

import com.example.comic_store.dto.ComicDTO;
import com.example.comic_store.dto.ComicDetailDTO;
import com.example.comic_store.entity.Comic;
import com.example.comic_store.repository.ComicRepository;
import com.example.comic_store.service.ComicService;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ComicServiceImpl implements ComicService {

    @Autowired
    private ComicRepository comicRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<ComicDTO> getListComicLanding(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Comic> comicPage = comicRepository.getComicLandingPage(pageable);
        return comicPage.stream()
                .map(element -> modelMapper.map(element, ComicDTO.class))
                .toList();
    }

    @Override
    public ComicDetailDTO getComicBy(Long id) {
        Comic comic = comicRepository.findById(id).orElse(null);
        return modelMapper.map(comic, ComicDetailDTO.class);
    }

    @Override
    public Page<ComicDTO> getPageComic(int page, int pageSize, Long typeComicId) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Comic> comicPage;
        if (typeComicId != null) {
            comicPage = comicRepository.getAllByTypeComicIdOrderByCreatedAt(pageable, typeComicId);
        } else {
            comicPage = comicRepository.getComicLandingPage(pageable);
        }
        return comicPage.map(element -> modelMapper.map(element, ComicDTO.class));
    }
}
