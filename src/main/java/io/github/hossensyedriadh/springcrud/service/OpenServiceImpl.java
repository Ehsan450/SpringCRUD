package io.github.hossensyedriadh.springcrud.service;

import io.github.hossensyedriadh.springcrud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class OpenServiceImpl implements OpenService {
    private final UserRepository userRepository;

    @Autowired
    public OpenServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isUsernameUnique(String username) {
        return this.userRepository.findById(username).isEmpty();
    }
}
