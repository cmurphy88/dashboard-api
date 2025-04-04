package com.example.dashboardapi.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserDetails {
  private UUID id;
  private String firstName;
  private String lastName;
  private String email;

  public UserDetails(User user) {
    this.id = user.getId();
    this.firstName = user.getFirstName();
    this.lastName = user.getLastName();
    this.email = user.getEmail();
  }
}
