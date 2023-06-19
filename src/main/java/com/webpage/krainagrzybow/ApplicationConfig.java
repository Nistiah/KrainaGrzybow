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
import com.webpage.krainagrzybow.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

@Configuration
@RequiredArgsConstructor
@EnableWebMvc
public class ApplicationConfig implements WebMvcConfigurer {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    private final OrderService orderService;
    private final OrderProductRepository orderProductRepository;
    private final OrderProductService orderProductService;

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {

            String[] mushroomNames = {
                    "Shiitake",
                    "Portobello",
                    "Chanterelle",
                    "Morel",
                    "Porcini",
                    "Enoki",
                    "Oyster",
                    "Lion's Mane",
                    "Maitake",
                    "Button Mushroom",
                    "Cremini",
                    "King Trumpet",
                    "Cordyceps",
                    "Reishi",
                    "Black Truffle",
                    "White Truffle",
                    "Hen of the Woods",
                    "Wood Ear",
                    "Lobster Mushroom",
                    "Penny Bun"
            };

            for (int i = 0; i < 20; i++) {
                String name = mushroomNames[i];
                String description = "A delicious " + name + " mushroom";
                BigDecimal price = BigDecimal.valueOf(Math.random() * (50.0) + 10.0);
                String image = "psychedelic-mushrooms-limited-colors-pattern_739548-636.png";

                Product product = Product.builder()
                        .name(name)
                        .description(description)
                        .price(price)
                        .image(image)
                        .build();

                productRepository.save(product);
            }

            User user = User.builder().name("admin").password("admin").role(Role.ROLE_ADMIN).email("paspatryk12@gmail.com").build();
            userRepository.save(user);

            Order cart = Order.builder()
                    .user(user)
                    .status(Status.SHOPPING_CART)
                    .orderProductsList(new ArrayList<>())
                    .date(LocalDate.now())
                    .build();
            Order wishlist = Order.builder()
                    .user(user)
                    .status(Status.WHISHLIST)
                    .orderProductsList(new ArrayList<>())
                    .build();
            orderRepository.save(cart);
            orderRepository.save(wishlist);

            OrderProduct orderProduct = OrderProduct.builder()
                    .product(productRepository.findById(1L).get())
                    .quantity(2)
                    .build();

            OrderProduct orderProduct2 = OrderProduct.builder()
                    .product(productRepository.findById(2L).get())
                    .quantity(3)
                    .build();

            OrderProduct orderProduct3 = OrderProduct.builder()
                    .product(productRepository.findById(3L).get())
                    .quantity(7)
                    .build();

            orderProductRepository.save(orderProduct);
            orderProductRepository.save(orderProduct2);
            orderProductRepository.save(orderProduct3);

            orderProductService.addOrderProductToOrder(orderProduct, cart);
            orderProductService.addOrderProductToOrder(orderProduct2, cart);
            orderProductService.addOrderProductToOrder(orderProduct3, cart);

            orderRepository.save(cart);

//            orderService.sendInvoice("paspatryk12@gmail.com",cart.getId(), "Patrigo", "ul. Przedszkolna 1", "00-000", "Warszawa", "1234567890");
        };
    }
}
