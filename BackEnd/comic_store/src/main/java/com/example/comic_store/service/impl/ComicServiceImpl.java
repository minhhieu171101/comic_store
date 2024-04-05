package com.example.comic_store.service.impl;

import com.example.comic_store.dto.ComicAdminDTO;
import com.example.comic_store.dto.ComicDTO;
import com.example.comic_store.dto.ComicDetailDTO;
import com.example.comic_store.dto.ServiceResult;
import com.example.comic_store.entity.Comic;
import com.example.comic_store.entity.TypeComic;
import com.example.comic_store.repository.ComicRepository;
import com.example.comic_store.repository.TypeComicRepository;
import com.example.comic_store.service.ComicService;
import com.example.comic_store.service.mapper.ComicAdminMapper;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ComicServiceImpl implements ComicService {

    @Autowired
    private ComicRepository comicRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ComicAdminMapper comicAdminMapper;

    @Autowired
    private TypeComicRepository typeComicRepository;

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

    @Override
    public Page<ComicAdminDTO> getComicAdmin(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Object[]> comicPage = comicRepository.getAllComic(pageable);
        return comicAdminMapper.toComicAdminPageDTO(comicPage);
    }

    @Override
    public ServiceResult<String> updateComic(ComicAdminDTO comicAdminDTO) {
        TypeComic typeComic = typeComicRepository.findTypeComicByTypeName(comicAdminDTO.getTypeName());
        Comic comic = comicAdminMapper.toComic(comicAdminDTO);
        if (typeComic != null) {
            comic.setTypeComicId(typeComic.getId());
        }
        comicRepository.save(comic);
        ServiceResult<String> result = new ServiceResult<>();
        result.setStatus(HttpStatus.OK);
        if (comicAdminDTO.getId() != null) {
            result.setMessage("Cập nhật truyện thành công!");
        } else {
            result.setMessage("Thêm mới truyện thành công!");
        }
        return result;
    }

    @Override
    public ServiceResult<String> deleteComic(ComicAdminDTO comicAdminDTO) {
        Optional<Comic> comic = comicRepository.findById(comicAdminDTO.getId());
        ServiceResult<String> result = new ServiceResult<>();
        if (comic.isPresent()) {
            comicRepository.delete(comic.orElse(null));
            result.setStatus(HttpStatus.OK);
            result.setMessage("Xóa truyện thành công!");
            return result;
        }
        result.setStatus(HttpStatus.BAD_REQUEST);
        result.setMessage("Xóa truyện không thành công!");
        return result;
    }
}
