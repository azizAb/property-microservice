package com.aziz.users.users.services;

import org.springframework.data.domain.Page;

import com.aziz.users.users.dto.UserDto;

public interface UserServiceInterface {
    Page<UserDto> getAllUsers(int pageNum, int pageSize);
    UserDto getUserById(Long id);
    UserDto createUser(String name);
}