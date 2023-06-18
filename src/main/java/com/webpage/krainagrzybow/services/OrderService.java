package com.webpage.krainagrzybow.services;

import com.webpage.krainagrzybow.enums.Status;
import com.webpage.krainagrzybow.mappers.OrderMapper;
import com.webpage.krainagrzybow.rdbms.models.Order;
import com.webpage.krainagrzybow.rdbms.models.OrderProduct;
import com.webpage.krainagrzybow.rdbms.models.Product;
import com.webpage.krainagrzybow.rdbms.models.User;
import com.webpage.krainagrzybow.rdbms.repositories.OrderProductRepository;
import com.webpage.krainagrzybow.rdbms.repositories.OrderRepository;
import com.webpage.krainagrzybow.rdbms.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.example.invoicegenerator.*;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderProductRepository orderProductRepository;
    private final AutenticationService autenticationService;
    private final ProductRepository productRepository;

    public Order getShoppingCart(Long userId) {
        return orderRepository.findByUserIdAndStatus(userId, Status.SHOPPING_CART);
    }

    public Order getWishList(Long userId) {
        return orderRepository.findByUserIdAndStatus(userId, Status.WHISHLIST);
    }

    public int getNumberOfProductsInCart(User user) {
        try {
            deleteOrderProductsIfProductIsDeleted(orderRepository.findByStatusAndUserId(Status.SHOPPING_CART, user.getId()).get());
            return orderRepository.findByStatusAndUserId(Status.SHOPPING_CART, user.getId()).get().getOrderProductsList().size();
        } catch (Exception e) {
            return 0;
        }
    }

    public int getNumberOfProductsInWishlist(User user) {
        try {
            deleteOrderProductsIfProductIsDeleted(orderRepository.findByStatusAndUserId(Status.WHISHLIST, user.getId()).get());
            return orderRepository.findByStatusAndUserId(Status.WHISHLIST, user.getId()).get().getOrderProductsList().size();
        } catch (Exception e) {
            return 0;
        }
    }

    public void order(Long id) {
        Order order = orderRepository.findById(id).orElseThrow();
        order.setStatus(Status.IN_PROGRESS);
        orderRepository.save(order);
        User user = order.getUser();
        Order cart = Order.builder()
                .user(user)
                .status(Status.SHOPPING_CART)
                .orderProductsList(new ArrayList<>())
                .build();
        orderRepository.save(cart);
    }

    public void deleteOrderProductsIfProductIsDeleted(Order order) {
        List<OrderProduct> orderProducts = order.getOrderProductsList();
        for (OrderProduct orderProduct : orderProducts) {
            if (orderProduct.getProduct().isDeleted()) {
                orderProductRepository.delete(orderProduct);
            }
        }
    }

    public void addToCart(Long id) {
        Long userId = autenticationService.getLoggedUser().getId();
        Order cart = orderRepository.findByStatusAndUserId(Status.SHOPPING_CART, userId).orElseThrow();
        Product product = productRepository.findById(id).orElseThrow();
        if (cart.isProductInOrder(id)) {
            cart.getOrderProductByProductId(id).setQuantity(cart.getOrderProductByProductId(id).getQuantity() + 1);
            orderProductRepository.save(cart.getOrderProductByProductId(id));
            return;
        }
        OrderProduct orderProduct = OrderProduct.builder()
                .product(product)
                .quantity(1)
                .build();
        orderProductRepository.save(orderProduct);
        cart.addOrderProduct(orderProduct);
        orderRepository.save(cart);
    }

    public void removeFromCart(Long id) {
        Long userId = autenticationService.getLoggedUser().getId();
        Order cart = orderRepository.findByStatusAndUserId(Status.SHOPPING_CART, userId).orElseThrow();
        cart.removeOrderProduct(cart.getOrderProductByProductId(id));
        orderRepository.save(cart);
    }

    public void addToWishList(Long id) {
        Long userId = autenticationService.getLoggedUser().getId();
        Order wishList = orderRepository.findByStatusAndUserId(Status.WHISHLIST, userId).orElseThrow();
        Product product = productRepository.findById(id).orElseThrow();
        if (wishList.isProductInOrder(id)) {
            return;
        }
        OrderProduct orderProduct = OrderProduct.builder()
                .product(product)
                .quantity(1)
                .build();
        orderProductRepository.save(orderProduct);
        wishList.addOrderProduct(orderProduct);
        orderRepository.save(wishList);
    }

    public void removeFromWishList(Long id) {
        Long userId = autenticationService.getLoggedUser().getId();
        Order whishList = orderRepository.findByStatusAndUserId(Status.WHISHLIST, userId).orElseThrow();
        whishList.removeOrderProduct(whishList.getOrderProductByProductId(id));
        orderRepository.save(whishList);
    }

    //FIXME: do bazy trzeba bedzie dac chyba
    long counter = 0;

    public void sendInvoice(Long id) {
        Order order = orderRepository.findById(id).orElseThrow();
        List<ProductGen> products = new ArrayList<>();
        for (OrderProduct orderProduct : order.getOrderProductsList()) {
            products.add(new ProductGen(orderProduct.getProduct().getName(),null,UnitOfMeasure.GRAM,orderProduct.getQuantity(), orderProduct.getProduct().getPrice().doubleValue(), 0.23 ));
        }

        InvoiceGenerator invoiceGenerator = new InvoiceGenerator(
                order.getDate(),
                counter,
                new SellerInfo("Kraina Grzybów", "ul. Grzybowa 1", "Warszawa", "00-000", "019283", "0000 1111 2222 3333", "+048 111111111"),
                new BuyerInfo(order.getUser().getName() + " ", order.getAddress(), order.getAddress(), order.getAddress(), order.getPhoneNumber()),
                new ReceiverInfo(order.getUser().getName() + " ", order.getAddress(), order.getAddress(), order.getAddress()),
                products);

        invoiceGenerator.generateInvoice("output.pdf");


//        InvoiceGenerator invoiceGenerator = new InvoiceGenerator();

//        InvoiceGenerator invoiceGenerator = new InvoiceGenerator();

//        order.setStatus(Status.INVOICE_SENT);
//        orderRepository.save(order);
//    }

    }


}

