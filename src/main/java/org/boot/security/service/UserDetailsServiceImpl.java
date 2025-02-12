package org.boot.security.service;

import lombok.RequiredArgsConstructor;
import org.boot.security.entity.User;
import org.boot.security.exceptions.NoUsersFoundException;
import org.boot.security.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws NoUsersFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new NoUsersFoundException(username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), List.of());
    }
}
