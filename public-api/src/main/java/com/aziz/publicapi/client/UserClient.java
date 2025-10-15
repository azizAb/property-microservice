package com.aziz.publicapi.client;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.aziz.publicapi.dto.ApiResponse;
import com.aziz.publicapi.dto.UserRequest;
import com.aziz.publicapi.model.User;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;

import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UserClient {
    private final WebClient.Builder webClientBuilder;

    @Value("${services.user.base-url}")
    private String baseUrl;

    public Mono<User> createUser(UserRequest request) {
        return webClientBuilder.baseUrl(baseUrl).build()
            .post()
            .uri(uriBuilder -> uriBuilder
                .path("/users")
                .queryParam("name", request.getName())
                .build())
            .retrieve()
            .bodyToMono(new ParameterizedTypeReference<ApiResponse<User>>() {})
            .flatMap(response -> {
                return Mono.justOrEmpty(response.getData());
            });
    }

    public Mono<User> getUserById(Long id) {
        return webClientBuilder.baseUrl(baseUrl).build()
            .get()
            .uri("/users/{id}", id)
            .retrieve()
            .bodyToMono(new ParameterizedTypeReference<ApiResponse<User>>() {})
            .flatMap(wrapper -> {
                if (wrapper.isResult() && wrapper.getData() != null) {
                    return Mono.just(wrapper.getData());
                } else {
                    return Mono.empty();
                }
            });
    }
}
