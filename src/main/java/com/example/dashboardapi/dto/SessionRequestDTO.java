package com.example.dashboardapi.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class SessionRequestDTO {
  private String name;
  private UUID userId;
  private LocalDateTime date;
  private List<ExerciseDTO> exercises = new ArrayList<>();
}