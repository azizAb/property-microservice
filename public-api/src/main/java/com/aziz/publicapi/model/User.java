package com.aziz.publicapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class User {
    private Long id;
    private String name;
    @JsonProperty("created_at")
    private long createdAt;
    @JsonProperty("updated_at") 
    private long updatedAt;   
}
