package org.boot.security.controller;

import lombok.RequiredArgsConstructor;
import org.boot.security.dto.UserDTO;
import org.boot.security.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.tinylog.Logger;

import java.util.List;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/save")
    public ResponseEntity<UserDTO> save(@ModelAttribute UserDTO user) {
        Logger.info("save user: " + user);
        userService.saveWithRole(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //username and role
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin/users")
    public ResponseEntity<List<UserDTO>> getAll() {
        Logger.info("get all users");
        return ResponseEntity.ok(userService.getAllUsers());
    }

    //get user by role
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("admin/roles/{role}")
    public ResponseEntity<List<UserDTO>> getRoles(@PathVariable String role) {
        Logger.info("get role: " + role);
        return ResponseEntity.ok(userService.getUsersByRole(role));
    }
}
