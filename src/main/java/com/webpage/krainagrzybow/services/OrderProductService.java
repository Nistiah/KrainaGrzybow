package com.webpage.krainagrzybow.services;

import com.webpage.krainagrzybow.rdbms.models.Order;
import com.webpage.krainagrzybow.rdbms.models.OrderProduct;
import com.webpage.krainagrzybow.rdbms.models.Product;
import com.webpage.krainagrzybow.rdbms.repositories.OrderProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderProductService {

    private final OrderProductRepository orderProductRepository;

    public boolean addOrderProductToOrder(OrderProduct orderProduct, Order order) {
        orderProduct.setOrder(order);

        order.getOrderProducts().add(orderProduct);
        orderProductRepository.save(orderProduct);
        return true;
    }


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

