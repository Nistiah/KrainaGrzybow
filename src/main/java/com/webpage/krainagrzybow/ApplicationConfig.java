package com.webpage.krainagrzybow;

import com.webpage.krainagrzybow.rdbms.models.Product;
import com.webpage.krainagrzybow.rdbms.repositories.ProductRepository;
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
public class ApplicationConfig  implements WebMvcConfigurer {

    private final ProductRepository productRepository;
    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            Product product = new Product();
            product.setName("test");
            product.setDescription("test");
            product.setPrice(BigDecimal.valueOf( 2.0));
            product.setPromotion(BigDecimal.valueOf( 1.0));
            product.setImage("test");

            Product product2 = new Product();
            product2.setName("test2");
            product2.setDescription("test2");
            product2.setPrice(BigDecimal.valueOf( 2.0));
            product2.setPromotion(BigDecimal.valueOf( 1.0));
            product2.setImage("test2");

            Product product3 = new Product();
            product3.setName("test3");
            product3.setDescription("test3");
            product3.setPrice(BigDecimal.valueOf( 2.0));
            product3.setPromotion(BigDecimal.valueOf( 1.0));
            product3.setImage("test3");

            productRepository.save(product);
            productRepository.save(product2);
            productRepository.save(product3);




        };
    }


}
