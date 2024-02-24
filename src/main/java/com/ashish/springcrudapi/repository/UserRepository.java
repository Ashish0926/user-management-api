package com.ashish.springcrudapi.repository;

import com.ashish.springcrudapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
