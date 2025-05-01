package com.example.dashboardapi.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class ExerciseDTO {
  private UUID id;
  private String name;
  private List<SetDTO> sets = new ArrayList<>();
}