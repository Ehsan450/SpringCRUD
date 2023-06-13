package io.github.hossensyedriadh.springcrud.configuration;

import io.github.hossensyedriadh.springcrud.entity.User;
import io.github.hossensyedriadh.springcrud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = this.userRepository.findById(username);

        if (user.isPresent()) {
            User matchedUser = user.get();

            UserDetails userDetails = new UserDetails() {
                @Override
                public Collection<? extends GrantedAuthority> getAuthorities() {
                    return Collections.singletonList(new SimpleGrantedAuthority(matchedUser.getAuthority().toString()));
                }

                @Override
                public String getPassword() {
                    return matchedUser.getPassword();
                }

                @Override
                public String getUsername() {
                    return matchedUser.getUsername();
                }

                @Override
                public boolean isAccountNonExpired() {
                    return true;
                }

                @Override
                public boolean isAccountNonLocked() {
                    return true;
                }

                @Override
                public boolean isCredentialsNonExpired() {
                    return true;
                }

                @Override
                public boolean isEnabled() {
                    return matchedUser.isEnabled();
                }
            };

            return org.springframework.security.core.userdetails.User.withUserDetails(userDetails).build();
        }

        throw new UsernameNotFoundException("User not found: " + username);
    }
}
