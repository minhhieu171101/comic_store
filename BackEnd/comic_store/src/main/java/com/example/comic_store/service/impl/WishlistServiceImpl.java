package com.example.comic_store.service.impl;

import com.example.comic_store.dto.ServiceResult;
import com.example.comic_store.dto.WishlistDTO;
import com.example.comic_store.entity.Wishlist;
import com.example.comic_store.repository.WishlistRepository;
import com.example.comic_store.service.WishlistService;
import com.example.comic_store.service.mapper.WishlistMapper;
import java.time.LocalDateTime;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class WishlistServiceImpl implements WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private WishlistMapper wishlistMapper;

    @Autowired
    private ModelMapper modelMapper;

//    @Override
//    public Page<WishlistDTO> getWishListPage(WishlistDTO wishlistDTO) {
//        Pageable pageable = PageRequest.of(wishlistDTO.getPage(), wishlistDTO.getPageSize());
//        Page<Object[]> wishlistPage = wishlistRepository.getAllWishList(pageable, wishlistDTO.getUsername());
//        return wishlistMapper.toWishlistDTOPage(wishlistPage);
//    }

    @Override
    public ServiceResult<String> addToWishlist(WishlistDTO wishlistDTO) {
        Wishlist wishlist = modelMapper.map(wishlistDTO, Wishlist.class);
        wishlist.setCreatedAt(LocalDateTime.now());
        wishlistRepository.save(wishlist);
        ServiceResult<String> result = new ServiceResult<>();
        result.setMessage("Thêm vào danh sách yêu thích thành cồng!");
        result.setStatus(HttpStatus.OK);
        return result;
    }
}
