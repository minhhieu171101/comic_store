package com.example.comic_store.service.mapper;

import com.example.comic_store.dto.WishlistDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

@Component
public class WishlistMapper {

    public WishlistDTO toWishlistDTO(Object[] object) {
        WishlistDTO wishlistDTO = new WishlistDTO();
        wishlistDTO.setId((Long) object[0]);
        wishlistDTO.setComicId((Long) object[1]);
        wishlistDTO.setCreatedBy((Long) object[2]);
        wishlistDTO.setComicName((String) object[3]);
        wishlistDTO.setComicName((String) object[4]);
        wishlistDTO.setTypeComicName((String) object[5]);
        wishlistDTO.setContents((String) object[6]);
        wishlistDTO.setComicImg((String) object[7]);
        wishlistDTO.setUsername((String) object[7]);
        return wishlistDTO;
    }

    public Page<WishlistDTO> toWishlistDTOPage(Page<Object[]> objects) {
        List<WishlistDTO> wishlistDTOList = new ArrayList<>();
        for (Object[] object : objects) {
            wishlistDTOList.add(toWishlistDTO(object));
        }
        return new PageImpl<>(wishlistDTOList, objects.getPageable(), objects.getTotalElements());
    }
}
