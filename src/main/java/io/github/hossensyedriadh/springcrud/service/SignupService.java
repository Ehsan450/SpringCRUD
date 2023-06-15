package io.github.hossensyedriadh.springcrud.service;

import io.github.hossensyedriadh.springcrud.entity.User;

public sealed interface SignupService permits SignupServiceImpl {
    void signup(User user);
}
