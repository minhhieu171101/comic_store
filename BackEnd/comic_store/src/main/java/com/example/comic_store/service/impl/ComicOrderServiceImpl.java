package com.example.comic_store.service.impl;

import com.example.comic_store.dto.ComicOrderDTO;
import com.example.comic_store.dto.ServiceResult;
import com.example.comic_store.dto.StatisticComicDTO;
import com.example.comic_store.entity.ComicOrder;
import com.example.comic_store.repository.ComicOrderRepository;
import com.example.comic_store.service.ComicOrderService;
import com.example.comic_store.service.mapper.ComicOrderMapper;
import com.example.comic_store.service.mapper.StatisticMonthMapper;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ComicOrderServiceImpl implements ComicOrderService {

    @Autowired
    private ComicOrderRepository comicOrderRepository;

    @Autowired
    private ComicOrderMapper comicOrderMapper;

    @Autowired
    private StatisticMonthMapper statisticMonthMapper;

    @Override
    public List<ComicOrderDTO> getListComicOrder(String username) {
        List<Object[]> comicOrders = comicOrderRepository.getAllByUsername(username);
        return comicOrderMapper.comicOrderDTOList(comicOrders);
    }

    @Override
    public ServiceResult<String> createComicOrder(ComicOrderDTO comicOrderDTO) {
        ComicOrder comicOrder = comicOrderMapper.toComicOrder(comicOrderDTO);
        comicOrder.setCreatedAt(LocalDateTime.now());
        comicOrder.setUpdatedAt(LocalDateTime.now());
        comicOrder.setUpdatedBy(comicOrderDTO.getUserId());
        comicOrder.setStatus(0L);

        comicOrderRepository.save(comicOrder);
        ServiceResult<String> result = new ServiceResult<>();
        result.setStatus(HttpStatus.OK);
        result.setData("Create successfully!");
        result.setMessage("Thêm vào giỏ hàng thành công!");
        return result;
    }

    @Override
    public ServiceResult<String> deleteComicOrder(Long comicOrderId) {
        ServiceResult<String> result = new ServiceResult<>();
        Optional<ComicOrder> comicOrder = comicOrderRepository.findById(comicOrderId);
        if (comicOrder.isPresent()) {
            comicOrderRepository.delete(comicOrder.orElse(null));
            result.setMessage("Xóa sản phẩm thành công!");
            result.setData("Delete successfully!");
            result.setStatus(HttpStatus.OK);
            return result;
        }
        result.setMessage("Xóa sản phẩm thất bại!");
        result.setData("Delete Failed!");
        result.setStatus(HttpStatus.BAD_REQUEST);
        return result;
    }

    @Override
    public List<StatisticComicDTO> getStatisticComic() {
        List<Object[]> statisticList = comicOrderRepository.getStatisticMonth(LocalDateTime.now().minusMonths(1l));
        return statisticMonthMapper.toSatisticComicDTOList(statisticList);
    }

}
