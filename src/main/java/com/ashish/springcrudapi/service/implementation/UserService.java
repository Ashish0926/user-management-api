package com.ashish.springcrudapi.service.implementation;

import com.ashish.springcrudapi.dto.request.UserRequest;
import com.ashish.springcrudapi.dto.response.UserResponse;
import com.ashish.springcrudapi.entity.User;
import com.ashish.springcrudapi.repository.UserRepository;
import com.ashish.springcrudapi.service.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public UserResponse createUser(final UserRequest userRequest) {
        final User user = modelMapper.map(userRequest, User.class);
        final User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserResponse.class);
    }
}
