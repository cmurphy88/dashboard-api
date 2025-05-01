package com.example.dashboardapi.dto;

import com.example.dashboardapi.model.Session;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class SessionResponseDTO {
  private UUID id;
  private String name;
  private UUID userId;
  private LocalDateTime date;
  private List<ExerciseSetResponseDTO> exercises = new ArrayList<>();

  public SessionResponseDTO() {
  }
}