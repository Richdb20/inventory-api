package com.example.inventory_api.dto;

public record LoginResponse(
        String token,
        String tokenType,
        long expiresInSeconds
) {
}
