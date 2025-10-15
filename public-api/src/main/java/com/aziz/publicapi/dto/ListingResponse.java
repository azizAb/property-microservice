package com.aziz.publicapi.dto;

import java.util.List;

import com.aziz.publicapi.model.Listing;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Slf4j
public class ListingResponse {
    private boolean result;
    private List<Listing> listings;

    public static ListingResponse success(List<Listing> listings) {
        return new ListingResponse(true, listings);
    }

    public static ListingResponse failure(String error) {
        log.error("ListingResponse failure: {}", error);
        return new ListingResponse(false, null);
    }
}
