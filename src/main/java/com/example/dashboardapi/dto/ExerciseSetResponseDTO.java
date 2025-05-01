package com.example.dashboardapi.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class ExerciseSetResponseDTO {
  private UUID exercise_id;
  private String exercise_name;
  private List<SetResponseDTO> sets = new ArrayList<>();

  public void addSet(SetResponseDTO set) {
    sets.add(set);
  }
}
