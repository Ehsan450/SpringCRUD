package io.github.hossensyedriadh.springcrud.service;

public sealed interface OpenService permits OpenServiceImpl {
    boolean isUsernameUnique(String username);
}
