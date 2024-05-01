package com.ashish.springcrudapi.service;

import static com.ashish.springcrudapi.constant.PrometheusCounter.CREATE_USER_TOTAL;
import static com.ashish.springcrudapi.constant.PrometheusCounter.DELETE_USER_BY_ID_TOTAL;
import static com.ashish.springcrudapi.constant.PrometheusCounter.GET_USERS_TOTAL;
import static com.ashish.springcrudapi.constant.PrometheusCounter.GET_USER_BY_ID_TOTAL;
import static com.ashish.springcrudapi.constant.PrometheusCounter.UPDATE_USER_BY_ID_TOTAL;
import static com.ashish.springcrudapi.constant.ResponseMessages.USER_NOT_FOUND;
import static java.util.Objects.isNull;

import com.ashish.springcrudapi.dto.request.UserRequest;
import com.ashish.springcrudapi.dto.response.UserResponse;
import com.ashish.springcrudapi.entity.User;
import com.ashish.springcrudapi.exception.UserNotFoundException;
import com.ashish.springcrudapi.repository.UserRepository;
import io.micrometer.core.instrument.MeterRegistry;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    private final MeterRegistry meterRegistry;

    public UserService(final UserRepository userRepository, final MeterRegistry meterRegistry) {
        this.userRepository = userRepository;
        this.meterRegistry = meterRegistry;
        this.modelMapper = new ModelMapper();
    }

    public UserResponse createUser(final UserRequest userRequest) {
        meterRegistry.counter(CREATE_USER_TOTAL).increment();

        final User user = modelMapper.map(userRequest, User.class);
        final User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserResponse.class);
    }

    public UserResponse getUserById(final Long id) {
        meterRegistry.counter(GET_USER_BY_ID_TOTAL).increment();

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
        meterRegistry.counter(GET_USERS_TOTAL).increment();

        final List<User> users = userRepository.findAll();
        return mapToUserResponses(users);
    }

    private List<UserResponse> mapToUserResponses(final List<User> users) {
        return users.stream()
            .map(user -> modelMapper.map(user, UserResponse.class))
            .toList();
    }

    public UserResponse updateUserById(final Long id, final UserRequest userRequest) {
        meterRegistry.counter(UPDATE_USER_BY_ID_TOTAL).increment();

        final Optional<User> optionalUser = userRepository.findById(id);
        final User updatedUserDetails = updateUserDetails(id, userRequest, optionalUser);
        final User savedUser = userRepository.save(updatedUserDetails);
        return modelMapper.map(savedUser, UserResponse.class);
    }

    private User updateUserDetails(final Long id, final UserRequest userRequest, final Optional<User> optionalUser) {
        final User savedUser = validateUserExists(id, optionalUser);
        savedUser.setFirstName(userRequest.getFirstName());
        savedUser.setLastName(userRequest.getLastName());
        savedUser.setEmail(userRequest.getEmail());
        return savedUser;
    }

    public void deleteUserById(final Long id) {
        meterRegistry.counter(DELETE_USER_BY_ID_TOTAL).increment();

        final Optional<User> optionalUser = userRepository.findById(id);
        final User user = validateUserExists(id, optionalUser);
        userRepository.deleteById(user.getId());
    }
}
