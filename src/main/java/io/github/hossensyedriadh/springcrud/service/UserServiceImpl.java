package io.github.hossensyedriadh.springcrud.service;

import io.github.hossensyedriadh.springcrud.entity.User;
import io.github.hossensyedriadh.springcrud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public final class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> users() {
        return this.userRepository.findAll();
    }

    @Override
    public Optional<User> user(String username) {
        return this.userRepository.findById(username);
    }

    @Override
    public User save(User user) {
        return this.userRepository.saveAndFlush(user);
    }
}
