package com.aziz.publicapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ListingRequest {
    @JsonProperty("user_id")
    private Long userId;
    @JsonProperty("listing_type")
    private String listingType;
    private Integer price;
}
