package com.sunny.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sunny.blog.entities.User;
//import org.springframework.stereotype.Repository;

import java.util.Optional;

//@Repository
public interface UserRepo extends JpaRepository<User, Integer>{

    Optional<User> findByEmailId(String email);
}
