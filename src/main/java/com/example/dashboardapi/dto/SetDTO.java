package com.example.dashboardapi.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SetDTO {
  private Integer order;
  private Double weight;
  private Integer reps;
  private UUID sessionId;
  private UUID exerciseId;
}