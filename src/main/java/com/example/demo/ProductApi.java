package com.example.demo;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.client.ExchangeFilterFunctions.basicAuthentication;

@Component
class ProductApi {

    private WebClient apiClient = WebClient.builder()
            .filter(basicAuthentication("user", "pass"))
            .build();

    Mono<Product[]> getProducts() {
        return apiClient.get().uri("https://product-service.herokuapp.com/api/v1/products")
                .retrieve()
                .bodyToMono(Product[].class)
                .log();
    }

}
