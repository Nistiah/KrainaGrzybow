package com.webpage.krainagrzybow.services;

import com.webpage.krainagrzybow.dto.models.OrderProduct;
import com.webpage.krainagrzybow.dto.models.Product;
import com.webpage.krainagrzybow.dto.repositories.OrderProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class OrderProductService {

    @Autowired
    private OrderProductRepository orderProductRepository;

    public void addNewOrderProduct(Long orderId, Product product, Integer quantity) {
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setProduct(product);
        orderProduct.setQuantity(quantity);
        orderProductRepository.save(orderProduct);
    }

    public List<OrderProduct> getAllOrderProducts() {
        return orderProductRepository.findAll();
    }

    public OrderProduct saveOrderProduct(OrderProduct orderProduct) {
        return orderProductRepository.save(orderProduct);
    }
    public void deleteOrderProductById(Long id) {
        orderProductRepository.deleteById(id);
    }
}

