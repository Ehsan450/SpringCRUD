package io.github.hossensyedriadh.springcrud.service;

import io.github.hossensyedriadh.springcrud.entity.User;

import java.util.List;
import java.util.Optional;

public sealed interface UserService permits UserServiceImpl {
    List<User> users();

    Optional<User> user(String username);

    User save(User user);
}
