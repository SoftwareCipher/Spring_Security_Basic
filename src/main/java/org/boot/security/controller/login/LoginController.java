package org.boot.security.controller.login;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.boot.security.dto.UserDTO;
import org.boot.security.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/save")
    public String save() {
        return "save";
    }

    @PostMapping("/save")
    public String save(@Valid UserDTO userDTO,
                       BindingResult bindingResult,
                       Model model,
                       RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            final List<ObjectError> allErrors = bindingResult.getAllErrors();
            allErrors.forEach(error -> errors.put(error.getObjectName(),
                    error.getDefaultMessage()));
            model.addAttribute("errors", errors);
            return "/save";
        }
        userService.saveWithRole(userDTO);
       redirectAttributes.addFlashAttribute("userDTO", userDTO.getName());
        return "redirect:/";
    }

    @GetMapping("/error")
    public String error(){
        return "access_denied";
    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String showAllUsers(Model model) {
        List<UserDTO> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }
}
