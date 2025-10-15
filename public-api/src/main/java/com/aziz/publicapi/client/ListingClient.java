package com.aziz.publicapi.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.aziz.publicapi.dto.ListingRequest;
import com.aziz.publicapi.model.Listing;
import com.aziz.publicapi.dto.ApiResponse;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ListingClient {
    private final WebClient.Builder webClientBuilder;

    @Value("${services.property.base-url}")
    private String baseUrl;

    public Mono<ApiResponse<Listing>> getListings(Integer pageNum, Integer pageSize, Long userId) {
        WebClient client = webClientBuilder.baseUrl(baseUrl).build();

        return client.get()
            .uri(uriBuilder -> uriBuilder
                .path("/listings")
                .queryParam("page_num", pageNum)
                .queryParam("page_size", pageSize)
                .queryParamIfPresent("user_id", java.util.Optional.ofNullable(userId))
                .build())
            .retrieve()
            .bodyToMono(new ParameterizedTypeReference<ApiResponse<Listing>>() {});
    }

    public Mono<Listing> createListing(ListingRequest request) {
        return webClientBuilder.baseUrl(baseUrl).build()
            .post()
            .uri(uriBuilder -> uriBuilder
                .path("/listings")
                .queryParam("user_id", request.getUserId())
                .queryParam("listing_type", request.getListingType())
                .queryParam("price", request.getPrice())
                .build())
            .retrieve()
            .bodyToMono(new ParameterizedTypeReference<ApiResponse<Listing>>() {})
            .flatMap(response -> {
                return Mono.justOrEmpty(response.getData());
            });
    }
}
