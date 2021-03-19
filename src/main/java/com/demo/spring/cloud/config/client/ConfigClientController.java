package com.demo.spring.cloud.config.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class ConfigClientController {

    @Value("${user.role}")
    private String role;

    private final BlackListProps blackListProps;

    public ConfigClientController(BlackListProps blackListProps) {
        this.blackListProps = blackListProps;
    }

    @GetMapping(
            value = "/whoami/{username}",
            produces = MediaType.TEXT_PLAIN_VALUE)
    public String whoami(@PathVariable("username") String username) {
        if (blackListProps.getUsernames().contains(username)) {
            return String.format("Hello! You're %s and you are blocked", username);
        }
        return String.format("Hello! You're %s and you'll become a(n) %s...\n", username, role);
    }
}
