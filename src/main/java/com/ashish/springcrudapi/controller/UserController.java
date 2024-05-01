package com.ashish.springcrudapi.controller;

import static com.ashish.springcrudapi.constant.ResponseMessages.USER_DELETED_SUCCESSFULLY;

import com.ashish.springcrudapi.dto.request.UserRequest;
import com.ashish.springcrudapi.dto.response.SuccessResponseDto;
import com.ashish.springcrudapi.dto.response.UserResponse;
import com.ashish.springcrudapi.service.UserService;
import io.micrometer.core.instrument.MeterRegistry;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("v1/api/users")
@Tag(name = "User")
public class UserController {

    private UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {
        final UserResponse userResponse = userService.createUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable final Long id) {
        final UserResponse userResponse = userService.getUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        final List<UserResponse> userResponses = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(userResponses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUserById(@PathVariable final Long id, @RequestBody final UserRequest userRequest) {
        final UserResponse userResponse = userService.updateUserById(id, userRequest);
        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponseDto> deleteUserById(@PathVariable final Long id) {
        userService.deleteUserById(id);
        SuccessResponseDto successResponseDto = SuccessResponseDto.builder()
            .statusCode(HttpStatus.OK.name())
            .message(USER_DELETED_SUCCESSFULLY.replace("{id}", id.toString()))
            .build();
        return ResponseEntity.status(HttpStatus.OK).body(successResponseDto);
    }
}
