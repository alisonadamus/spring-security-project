package com.alisonadamus.spring_security_project.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/home")
    public String home(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        if (authentication.getPrincipal() instanceof OidcUser oidcUser) {
            username = oidcUser.getEmail();
        } else if (authentication.getPrincipal() instanceof OAuth2User oauth2User) {
            username = oauth2User.getAttribute("email");
        }

        model.addAttribute("username", username);
        return "home-page";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin-page";
    }

    @GetMapping("/logout")
    public String logout() {
        return "index";
    }

    @GetMapping("/403")
    public String accessDenied() {
        return "403";
    }
}
