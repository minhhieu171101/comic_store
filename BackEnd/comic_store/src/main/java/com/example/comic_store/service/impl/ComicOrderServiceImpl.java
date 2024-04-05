package com.example.comic_store.service.impl;

import com.example.comic_store.dto.ComicOrderDTO;
import com.example.comic_store.repository.ComicOrderRepository;
import com.example.comic_store.service.ComicOrderService;
import com.example.comic_store.service.mapper.ComicOrderMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComicOrderServiceImpl implements ComicOrderService {

    @Autowired
    private ComicOrderRepository comicOrderRepository;

    @Autowired
    private ComicOrderMapper comicOrderMapper;

    @Override
    public List<ComicOrderDTO> getListComicOrder(String username) {
        List<Object[]> comicOrders = comicOrderRepository.getAllByUsername(username);
        return comicOrderMapper.comicOrderDTOList(comicOrders);
    }
}
