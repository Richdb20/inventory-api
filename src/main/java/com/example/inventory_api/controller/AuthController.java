
package com.example.inventory_api.controller;

import com.example.inventory_api.dto.LoginRequest;
import com.example.inventory_api.dto.LoginResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtEncoder jwtEncoder;

    public AuthController(
            AuthenticationManager authenticationManager,
            JwtEncoder jwtEncoder) {

        this.authenticationManager = authenticationManager;
        this.jwtEncoder = jwtEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LoginRequest request) {

        try {
            Authentication authentication =
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    request.username(),
                                    request.password()
                            )
                    );

            Instant now = Instant.now();
            long expiresIn = 3600;
            Instant expiresAt = now.plusSeconds(expiresIn);

            JwtClaimsSet claims = JwtClaimsSet.builder()
                    .issuer("inventory-api")
                    .issuedAt(now)
                    .expiresAt(expiresAt)
                    .subject(authentication.getName())
                    .claim(
                            "authorities",
                            authentication.getAuthorities()
                                    .stream()
                                    .map(GrantedAuthority::getAuthority)
                                    .toList()
                    )
                    .build();

            JwsHeader header = JwsHeader
                    .with(MacAlgorithm.HS256)
                    .build();

            String token = jwtEncoder.encode(
                    JwtEncoderParameters.from(header, claims)
            ).getTokenValue();

            return ResponseEntity.ok(
                    new LoginResponse(
                            token,
                            "Bearer",
                            expiresIn
                    )
            );

        } catch (AuthenticationException exception) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of(
                            "error",
                            "Invalid username or password"
                    ));
        }
    }
}
