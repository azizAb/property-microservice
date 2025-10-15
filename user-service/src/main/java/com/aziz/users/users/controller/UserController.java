package com.aziz.users.users.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.aziz.users.users.dto.CreateUserRequest;
import com.aziz.users.users.dto.ResponseBody;
import com.aziz.users.users.dto.UserDto;
import com.aziz.users.users.services.UserServiceInterface;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserServiceInterface userService;

    @GetMapping
    public ResponseEntity<?> getUsers(
        @RequestParam(defaultValue = "1") int page_num, 
        @RequestParam(defaultValue = "10") int page_size) {
        log.info("GET /users - page_num: {}, page_size: {}", page_num, page_size);    

        List<UserDto> users = userService.getAllUsers(page_num, page_size).getContent();
        return ResponseEntity.ok().body(new ResponseBody(true, users));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        log.info("GET /users/{}", id);

        UserDto user = userService.getUserById(id);
        return ResponseEntity.ok().body(new ResponseBody(true, user));
    }

    @PostMapping
    public ResponseEntity<?> createUser(@Valid CreateUserRequest request) {
        UserDto user = userService.createUser(request.getName());
        return ResponseEntity.ok().body(new ResponseBody(true, user));
    }

}