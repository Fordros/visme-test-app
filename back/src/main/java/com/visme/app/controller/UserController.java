package com.visme.app.controller;


import com.visme.app.model.request.UserRequestDto;
import com.visme.app.model.response.UserResponseDto;
import com.visme.app.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    List<UserResponseDto> getUsers() {

        log.info("Request for get all users");

        return userService.getAllUsers();
    }

    @PostMapping
    UserResponseDto create(@RequestBody @Valid UserRequestDto dto) {

        log.info("Request for create user");

        return userService.create(dto);
    }

    @PutMapping("/{id}")
    UserResponseDto edit(@PathVariable Long id, @RequestBody @Valid UserRequestDto dto) {

        log.info("Request for edit user [{}]", id);

        dto.setId(id);
        return userService.edit(dto);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {

        log.info("Request for delete user by ID:[$id]");

        userService.delete(id);
    }
}
