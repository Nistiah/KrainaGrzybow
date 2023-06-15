package com.webpage.krainagrzybow;

import com.webpage.krainagrzybow.enums.Role;
import com.webpage.krainagrzybow.enums.Status;
import com.webpage.krainagrzybow.rdbms.models.Order;
import com.webpage.krainagrzybow.rdbms.models.OrderProduct;
import com.webpage.krainagrzybow.rdbms.models.Product;
import com.webpage.krainagrzybow.rdbms.models.User;
import com.webpage.krainagrzybow.rdbms.repositories.OrderProductRepository;
import com.webpage.krainagrzybow.rdbms.repositories.OrderRepository;
import com.webpage.krainagrzybow.rdbms.repositories.ProductRepository;
import com.webpage.krainagrzybow.rdbms.repositories.UserRepository;
import com.webpage.krainagrzybow.services.OrderProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.math.BigDecimal;

@Configuration
@RequiredArgsConstructor
@EnableWebMvc
public class ApplicationConfig implements WebMvcConfigurer {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final OrderProductService orderProductService;

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            Product product = new Product();
            product.setName("test");
            product.setDescription("test");
            product.setPrice(BigDecimal.valueOf(2.0));
            product.setPromotion(BigDecimal.valueOf(1.0));
            product.setImage("test");
            Product product2 = new Product();
            product2.setName("test2");
            product2.setDescription("test2");
            product2.setPrice(BigDecimal.valueOf(2.0));
            product2.setPromotion(BigDecimal.valueOf(1.0));
            product2.setImage("test2");
            Product product3 = new Product();
            product3.setName("test3");
            product3.setDescription("test3");
            product3.setPrice(BigDecimal.valueOf(2.0));
            product3.setPromotion(BigDecimal.valueOf(1.0));
            product3.setImage("test3");
            Product product4 = new Product();
            product4.setName("test4");
            product4.setDescription("test4test4test4test4");
            product4.setPrice(BigDecimal.valueOf(20.0));
            product4.setPromotion(BigDecimal.valueOf(1.0));
            product4.setImage("test4");
            Product product5 = new Product();
            product5.setName("test5");
            product5.setDescription("product5product5product5");
            product5.setPrice(BigDecimal.valueOf(200.0));
            product5.setPromotion(BigDecimal.valueOf(1.0));
            product5.setImage("test5");
            productRepository.save(product);
            productRepository.save(product2);
            productRepository.save(product3);
            productRepository.save(product4);
            productRepository.save(product5);
            User user = new User();
            user.setName("admin");
            user.setPassword("admin");
            user.setRole(Role.ROLE_ADMIN);
            user.setEmail("admin@admin.com");
            userRepository.save(user);
            Order order = new Order();
            order.setStatus(Status.SHOPPING_CART);
            order.setUser(user);
            orderRepository.save(order);
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setProduct(product);
            orderProduct.setQuantity(2);
            OrderProduct orderProduct2 = new OrderProduct();
            orderProduct2.setProduct(product2);
            orderProduct2.setQuantity(222);
            orderProductRepository.save(orderProduct);
            orderProductRepository.save(orderProduct2);
            orderProductService.addOrderProductToOrder(orderProduct, order);
            orderProductService.addOrderProductToOrder(orderProduct2, order);
            orderRepository.save(order);
        };
    }
}
