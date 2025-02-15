package org.boot.security.service;

import lombok.RequiredArgsConstructor;

import org.boot.security.dto.UserDTO;
import org.boot.security.entity.Role;
import org.boot.security.entity.User;
import org.boot.security.exceptions.NoUsersFoundException;
import org.boot.security.repository.RoleRepository;
import org.boot.security.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinylog.Logger;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public void saveWithRole(UserDTO dto) {
        Role role = roleRepository.findByName("USER")
                .orElseThrow(() -> new NullPointerException("Role not found with name: " + dto.getRole().getName()));
        User user = new User();
        user.setUsername(dto.getName());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(role);

        userRepository.save(user);
    }

    public List<UserDTO> getAllUsers() {
        List<UserDTO> users = userRepository.findAllUsers();

        if (users == null || users.isEmpty()) {
            Logger.error("No users found in the database");
            throw new NoUsersFoundException("No users found in the system");
        }

        return users;
    }

    @Transactional
    public List<UserDTO> getUsersByRole(String roleName) {
        return userRepository.findAllUsersByRoleName(roleName)
                .stream()
                .map(UserDTO::new)
                .toList();
    }
}
