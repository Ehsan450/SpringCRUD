package io.github.hossensyedriadh.springcrud.service;

import io.github.hossensyedriadh.springcrud.entity.User;
import io.github.hossensyedriadh.springcrud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import utility.AuthorityEnum;

@Service
public final class SignupServiceImpl implements SignupService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SignupServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void signup(User user) {
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        user.setAuthority(AuthorityEnum.ROLE_USER);
        this.userRepository.saveAndFlush(user);
    }
}
