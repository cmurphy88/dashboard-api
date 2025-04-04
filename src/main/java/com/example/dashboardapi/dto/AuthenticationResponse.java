package com.example.dashboardapi.dto;

import com.example.dashboardapi.model.UserDetails;

public record AuthenticationResponse(String token, UserDetails userDetails) {
}
