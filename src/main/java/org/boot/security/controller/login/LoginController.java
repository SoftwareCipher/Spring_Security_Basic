package org.boot.security.controller.login;

import lombok.RequiredArgsConstructor;
import org.boot.security.dto.UserDTO;
import org.boot.security.service.UserDetailsServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserDetailsServiceImpl userDetailsService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String customLogin() {
        return "login";
    }

//    @PostMapping("/login")
//    public ResponseEntity<UserDetails> processLogin(@RequestBody UserDTO login) {
//        userDetailsService.loadUserByUsername(login.getName());
//        return ResponseEntity.status(HttpStatus.FOUND).build();
//    }
}
