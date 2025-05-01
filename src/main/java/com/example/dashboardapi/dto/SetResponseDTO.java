package com.example.dashboardapi.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
public class SetResponseDTO {
  private UUID id;
  private int order;
  private double weight;
  private int reps;
  private UUID exercise_id;
}