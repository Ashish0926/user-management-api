package com.ashish.springcrudapi.service;

import static com.ashish.springcrudapi.constant.ResponseMessages.USER_NOT_FOUND;
import static java.util.Objects.isNull;

import com.ashish.springcrudapi.dto.request.UserRequest;
import com.ashish.springcrudapi.dto.response.UserResponse;
import com.ashish.springcrudapi.entity.User;
import com.ashish.springcrudapi.exception.UserNotFoundException;
import com.ashish.springcrudapi.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
        this.modelMapper = new ModelMapper();
    }

    public UserResponse createUser(final UserRequest userRequest) {
        final User user = modelMapper.map(userRequest, User.class);
        final User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserResponse.class);
    }

    public UserResponse getUserById(final Long id) {
        final Optional<User> optionalUser = userRepository.findById(id);
        final User user = validateUserExists(id, optionalUser);
        return modelMapper.map(user, UserResponse.class);
    }

    private static User validateUserExists(final Long id, final Optional<User> optionalUser) {
        final User user = optionalUser.orElse(null);
        if(isNull(user)) {
            throw new UserNotFoundException(HttpStatus.NOT_FOUND, USER_NOT_FOUND.replace("{id}", id.toString()));
        }
        return user;
    }

    public List<UserResponse> getAllUsers() {
        final List<User> users = userRepository.findAll();
        return mapToUserResponses(users);
    }

    private List<UserResponse> mapToUserResponses(final List<User> users) {
        return users.stream()
            .map(user -> modelMapper.map(user, UserResponse.class))
            .toList();
    }

    public UserResponse updateUserById(final Long id, final UserRequest userRequest) {
        final Optional<User> optionalUser = userRepository.findById(id);
        final User savedUser = validateUserExists(id, optionalUser);
        savedUser.setFirstName(userRequest.getFirstName());
        savedUser.setLastName(userRequest.getLastName());
        savedUser.setEmail(userRequest.getEmail());
        final User updatedUser = userRepository.save(savedUser);
        return modelMapper.map(updatedUser, UserResponse.class);
    }

    public void deleteUserById(final Long id) {
        final Optional<User> optionalUser = userRepository.findById(id);
        final User user = validateUserExists(id, optionalUser);
        userRepository.deleteById(user.getId());
    }
}
