package com.example.dashboardapi.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ExerciseDTO {
  private String name;
  private List<SetDTO> sets = new ArrayList<>();
}