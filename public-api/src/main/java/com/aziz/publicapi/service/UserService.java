package com.aziz.publicapi.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.aziz.publicapi.client.UserClient;
import com.aziz.publicapi.dto.UserRequest;
import com.aziz.publicapi.model.User;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserClient userClient;

    public Mono<Map<String, Object>> createUser(UserRequest request) {
        return userClient.createUser(request)
            .map(user -> {
                Map<String, Object> response = new HashMap<>();
                response.put("user", user);
                return response;
            });
    }

    public Mono<User> getUser(Long id) {
        return userClient.getUserById(id);
    }
}
