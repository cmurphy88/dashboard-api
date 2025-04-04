package com.example.dashboardapi.dto;

public record UserDTO (
    java.util.UUID id,
    String email,
    String firstName,
    String lastName
) {
}
