package com.alisonadamus.spring_security_project.controller;

import com.alisonadamus.spring_security_project.entity.Role;
import com.alisonadamus.spring_security_project.entity.User;
import com.alisonadamus.spring_security_project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register-page";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        if (userRepository.existsByLogin(user.getLogin())) {
            model.addAttribute("error", "A user with this login already exists.");
            return "register-page";
        }
        user.setRole(Role.USER);
        user.encodePassword(passwordEncoder);
        userRepository.save(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login-page";
    }
}
