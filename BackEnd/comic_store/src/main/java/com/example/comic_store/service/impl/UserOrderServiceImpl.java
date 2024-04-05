package com.example.comic_store.service.impl;

import com.example.comic_store.dto.PurchaseOrderDTO;
import com.example.comic_store.dto.ServiceResult;
import com.example.comic_store.dto.UserOrderDTO;
import com.example.comic_store.entity.ComicOrder;
import com.example.comic_store.entity.UserOrder;
import com.example.comic_store.repository.ComicOrderRepository;
import com.example.comic_store.repository.UserOrderRepository;
import com.example.comic_store.service.UserOrderService;
import com.example.comic_store.service.mapper.UserOrderMapper;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserOrderServiceImpl implements UserOrderService {

    @Autowired
    private UserOrderRepository userOrderRepository;

    @Autowired
    private ComicOrderRepository comicOrderRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserOrderMapper userOrderMapper;

    @Override
    public ServiceResult<String> saveUserOrder(UserOrderDTO userOrderDTO) {
        UserOrder userOrder = modelMapper.map(userOrderDTO, UserOrder.class);
        userOrder.setCreatedAt(LocalDateTime.now());
        userOrder.setUpdatedAt(LocalDateTime.now());
        userOrder.setOrderDate(LocalDate.now());
        UserOrder userResult = userOrderRepository.save(userOrder);

        List<ComicOrder> comicOrderList = comicOrderRepository.findAllById(userOrderDTO.getComicOrders());
        for (ComicOrder comicOrder : comicOrderList) {
            comicOrder.setUserOrderId(userResult.getId());
            comicOrder.setStatus(1L);
        }
        comicOrderRepository.saveAll(comicOrderList);

        ServiceResult<String> result = new ServiceResult<>();
        result.setData("sucessfully!");
        result.setMessage("Đặt hàng thành công!");
        result.setStatus(HttpStatus.OK);
        return result;
    }

    @Override
    public Page<PurchaseOrderDTO> getPagePurchaseOrder(PurchaseOrderDTO purchaseOrderDTO) {
        Pageable pageable = PageRequest.of(purchaseOrderDTO.getPage(), purchaseOrderDTO.getPageSize());
        Page<Object[]> objectPage = userOrderRepository.getAllPurchaseOrder(pageable);
        return userOrderMapper.toPurchaseOrderDTOPage(objectPage);
    }
}
