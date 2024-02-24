package com.ashish.springcrudapi.service;

import com.ashish.springcrudapi.dto.request.UserRequest;
import com.ashish.springcrudapi.dto.response.UserResponse;

public interface IUserService {

    UserResponse createUser(UserRequest user);
}
