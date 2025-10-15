package com.aziz.users.users.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.aziz.users.users.dto.UserDto;
import com.aziz.users.users.entity.User;
import com.aziz.users.users.exceptions.ResourceNotFoundException;
import com.aziz.users.users.mapper.UserMapper;
import com.aziz.users.users.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserServiceInterface {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public Page<UserDto> getAllUsers(int pageNum, int pageSize) {
        log.debug("Getting all users - Page: {}, Size: {}", pageNum, pageSize);

        Pageable pageable = PageRequest.of(
            pageNum - 1, 
            pageSize, 
            Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<User> users = userRepository.findAll(pageable);
        return users.map(userMapper::toResponse);
    }

    @Override
    public UserDto getUserById(Long id) {
        log.debug("Getting user by ID: {}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        
        return userMapper.toResponse(user);
    }

    @Override
    public UserDto createUser(String name) {
        log.debug("Creating user with name: {}", name);

        User user = User.builder()
                .name(name)
                .build();

        User savedUser = userRepository.save(user);
        log.info("User created successfully with ID: {}", savedUser.getId());

        return userMapper.toResponse(savedUser);
    }
}