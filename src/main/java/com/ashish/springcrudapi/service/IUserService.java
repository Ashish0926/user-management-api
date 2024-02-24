package com.ashish.springcrudapi.service;

import com.ashish.springcrudapi.dto.request.UserRequest;
import com.ashish.springcrudapi.dto.response.UserResponse;
import java.util.List;

public interface IUserService {

    UserResponse createUser(final UserRequest user);

    UserResponse getUserById(final String id);

    List<UserResponse> getAllUsers();
}
