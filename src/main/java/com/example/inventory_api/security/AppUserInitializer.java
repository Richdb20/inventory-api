
package com.example.inventory_api.security;

import com.example.inventory_api.model.AppUser;
import com.example.inventory_api.repository.AppUserRepository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AppUserInitializer implements CommandLineRunner {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${APP_BOOTSTRAP_USERNAME:}")
    private String username;

    @Value("${APP_BOOTSTRAP_PASSWORD:}")
    private String password;

    public AppUserInitializer(
            AppUserRepository appUserRepository,
            PasswordEncoder passwordEncoder) {

        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {

        if (username == null || username.isBlank()
                || password == null || password.isBlank()) {
            System.out.println(
                    "Bootstrap credentials not configured; skipping user creation.");
            return;
        }

        if (appUserRepository.findByUsername(username).isPresent()) {
            System.out.println(
                    "Bootstrap user already exists; skipping creation.");
            return;
        }

        String hashedPassword = passwordEncoder.encode(password);

        AppUser appUser = new AppUser(
                username,
                hashedPassword,
                "ROLE_ADMIN"
        );

        appUserRepository.save(appUser);

        System.out.println("Initial application user created successfully.");
    }
}
