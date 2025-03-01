package org.boot.security.controller.login;

import lombok.RequiredArgsConstructor;
import org.boot.security.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String customLogin() {
        return "login";
    }

    @GetMapping("/error")
    public String error(){
        return "access_denied";
    }
}
