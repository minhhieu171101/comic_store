package com.example.comic_store.service.mapper;

import com.example.comic_store.dto.ComicOrderDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ComicOrderMapper {

    public ComicOrderDTO toComicOrderDTO(Object[] object) {
        ComicOrderDTO comicOrderDTO = new ComicOrderDTO();
        comicOrderDTO.setUserId((Long) object[0]);
        comicOrderDTO.setComicId((Long) object[1]);
        comicOrderDTO.setComicOrderId((Long) object[2]);
        comicOrderDTO.setComicName((String) object[3]);
        comicOrderDTO.setImgComic((String) object[4]);
        comicOrderDTO.setQuantity((Long) object[5]);
        comicOrderDTO.setTotalPrice((Long) object[6]);
        comicOrderDTO.setTypeName((String) object[7]);
        return comicOrderDTO;
    }

    public List<ComicOrderDTO> comicOrderDTOList(List<Object[]> objects) {
        List<ComicOrderDTO> comicOrderDTOList = new ArrayList<>();
        for (Object[] object : objects) {
            comicOrderDTOList.add(toComicOrderDTO(object));
        }
        return comicOrderDTOList;
    }
}
