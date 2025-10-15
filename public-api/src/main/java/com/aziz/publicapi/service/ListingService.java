package com.aziz.publicapi.service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.aziz.publicapi.client.ListingClient;
import com.aziz.publicapi.client.UserClient;
import com.aziz.publicapi.dto.ListingRequest;
import com.aziz.publicapi.dto.ListingResponse;
import com.aziz.publicapi.model.Listing;
import com.aziz.publicapi.model.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class ListingService {

    private final ListingClient listingClient;
    private final UserClient userClient;

    public Mono<ListingResponse> getListings(Integer pageNum, Integer pageSize, Long userId) {
        Map<Long, Mono<User>> userCache = new ConcurrentHashMap<>();

        return listingClient.getListings(pageNum, pageSize, userId)
            .flatMapMany(wrapper -> {
                if (wrapper == null || !wrapper.isResult() || wrapper.getListings() == null) {
                    return Flux.empty();
                }
                return Flux.fromIterable(wrapper.getListings());
            })
            .flatMap(listing -> {
                Long uid = listing.getUserId();
    
                Mono<User> cachedUserMono = userCache.computeIfAbsent(uid, id ->
                    userClient.getUserById(id)
                    .onErrorResume(WebClientResponseException.NotFound.class, e -> Mono.empty())
                    .cache()
                );

                return cachedUserMono
                .doOnNext(user -> listing.setUser(user))
                .thenReturn(listing)
                .defaultIfEmpty(listing);
            })
            .sort((l1, l2) -> l2.getCreatedAt().compareTo(l1.getCreatedAt()))
            .collectList()
            .map(ListingResponse::success)
            .defaultIfEmpty(ListingResponse.success(java.util.Collections.emptyList()))
            .onErrorResume(ex -> Mono.just(ListingResponse.failure(ex.getMessage())));
    }

    public Mono<Map<String, Listing>> createListing(ListingRequest request) {
        return listingClient.createListing(request)
            .map(listing -> {
                Map<String, Listing> response = new HashMap<String, Listing>();
                response.put("listing", listing);
                return response;
            });
    }
}
