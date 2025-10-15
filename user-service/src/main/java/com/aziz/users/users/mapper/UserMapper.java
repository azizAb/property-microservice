package com.aziz.users.users.mapper;

import org.springframework.stereotype.Component;

import com.aziz.users.users.dto.UserDto;
import com.aziz.users.users.entity.User;

@Component
public class UserMapper {
    public UserDto toResponse(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
