package com.example.comic_store.service.mapper;

import com.example.comic_store.dto.ComicAdminDTO;
import com.example.comic_store.entity.Comic;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

@Component
public class ComicAdminMapper {

    public ComicAdminDTO toComicAdminDTO(Object[] object) {
        ComicAdminDTO comicAdminDTO = new ComicAdminDTO();
        comicAdminDTO.setId((Long) object[0]);
        comicAdminDTO.setComicName((String) object[1]);
        comicAdminDTO.setAuthorName((String) object[2]);
        comicAdminDTO.setImgComic((String) object[3]);

        // Chuẩn hóa tên thể loại truyện
        String[] typeName = ((String) object[4]).split(",");
        StringBuilder typeNameStandard = new StringBuilder();
        for (int i = 0; i < typeName.length; i++) {
            if (!typeName[i].equals(" ")) {
                typeNameStandard.append(typeName[i]);
            }
            if (i < typeName.length - 1 && !typeName[i+1].equals(" ")) {
                typeNameStandard.append(", ");
            }
        }
        comicAdminDTO.setTypeName(typeNameStandard.toString());
        comicAdminDTO.setReleaseDate((LocalDate) object[5]);
        comicAdminDTO.setPrice((Long) object[6]);
        comicAdminDTO.setSale((Long) object[7]);
        comicAdminDTO.setContents((String) object[8]);
        return comicAdminDTO;
    }

    public Page<ComicAdminDTO> toComicAdminPageDTO(Page<Object[]> objects) {
        List<ComicAdminDTO> comicAdminDTOList = new ArrayList<>();
        for (Object[] object: objects) {
            comicAdminDTOList.add(toComicAdminDTO(object));
        }
        return new PageImpl<>(comicAdminDTOList, objects.getPageable(), objects.getTotalElements());
    }

    public Comic toComic(ComicAdminDTO comicAdminDTO) {
        Comic comic = new Comic();
        comic.setId(comicAdminDTO.getId());
        comic.setComicName(comicAdminDTO.getComicName());
        comic.setAuthorName(comicAdminDTO.getAuthorName());
        comic.setImgComic(comicAdminDTO.getImgComic());
        comic.setPrice(comicAdminDTO.getPrice());
        comic.setSale(comicAdminDTO.getSale());
        comic.setReleaseDate(comicAdminDTO.getReleaseDate());
        comic.setResidualQuantity(comicAdminDTO.getResidualQuantity());
        comic.setContents(comicAdminDTO.getContents());
        return comic;
    }
}
