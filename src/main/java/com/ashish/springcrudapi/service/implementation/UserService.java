package com.ashish.springcrudapi.service.implementation;

import static com.ashish.springcrudapi.constant.ExceptionMessages.USER_NOT_FOUND;
import static java.util.Objects.isNull;

import com.ashish.springcrudapi.dto.request.UserRequest;
import com.ashish.springcrudapi.dto.response.UserResponse;
import com.ashish.springcrudapi.entity.User;
import com.ashish.springcrudapi.exception.UserNotFoundException;
import com.ashish.springcrudapi.repository.UserRepository;
import com.ashish.springcrudapi.service.IUserService;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public UserResponse createUser(final UserRequest userRequest) {
        final User user = modelMapper.map(userRequest, User.class);
        final User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserResponse.class);
    }

    @Override
    public UserResponse getUserById(final String id) {
        final Optional<User> optionalUser = userRepository.findById(Long.parseLong(id));
        final User user = optionalUser.orElse(null);
        if(isNull(user)) {
            throw new UserNotFoundException(HttpStatus.NOT_FOUND, USER_NOT_FOUND.replace("{id}", id));
        }
        return modelMapper.map(user, UserResponse.class);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        final List<User> users = userRepository.findAll();
        return mapToUserResponses(users);
    }

    private List<UserResponse> mapToUserResponses(final List<User> users) {
        return users.stream().map(user -> modelMapper.map(user, UserResponse.class)).toList();
    }
}
