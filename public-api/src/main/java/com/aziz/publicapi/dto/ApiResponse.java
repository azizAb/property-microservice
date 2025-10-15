package com.aziz.publicapi.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ApiResponse<T> {
    private boolean result;
    @JsonAlias({"data", "listing"})
    private T data;
    private List<T> listings;
}
