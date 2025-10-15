package com.aziz.publicapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aziz.publicapi.dto.ListingRequest;
import com.aziz.publicapi.dto.ListingResponse;
import com.aziz.publicapi.model.Listing;
import com.aziz.publicapi.service.ListingService;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/public-api/listings")
@RequiredArgsConstructor
public class ListingController {

    private final ListingService listingService;

    @GetMapping
    public Mono<ListingResponse> getListings(
        @RequestParam(defaultValue = "1") int pageNum,
        @RequestParam(defaultValue = "10") int pageSize,
        @RequestParam(required = false) Long userId
    ) {
        return listingService.getListings(pageNum, pageSize, userId);
    }

    @PostMapping
    public Mono<Map<String, Listing>> createListing(@RequestBody ListingRequest listingRequest) {
        return listingService.createListing(listingRequest);
    }

}
