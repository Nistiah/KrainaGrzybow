package com.webpage.krainagrzybow.services;

import com.webpage.krainagrzybow.dtos.OrderDto;
import com.webpage.krainagrzybow.enums.Status;
import com.webpage.krainagrzybow.mappers.OrderMapper;
import com.webpage.krainagrzybow.rdbms.models.Order;
import com.webpage.krainagrzybow.rdbms.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public void deleteOrderById(Long id) {
        orderRepository.deleteById(id);
    }

    public List<OrderDto> findAllOrders() {
        return orderRepository.findAll().stream().map(orderMapper::mapToDto).toList();
    }

    public OrderDto findOrderById(Long id) {
        return orderMapper.mapToDto(orderRepository.findById(id).orElse(null));
    }

    public OrderDto getCartByUserId(Long id) {

            return orderMapper.mapToDto(orderRepository.findByUserIdAndStatus(id, Status.NEW));

//        return orderRepository.findByUserIdAndStatus(id, Status.NEW);

//        return orderRepository.findByUserIdAndStatus(id,  Status.NEW);
    }



}

