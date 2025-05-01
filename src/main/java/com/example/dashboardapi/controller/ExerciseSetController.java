package com.example.dashboardapi.controller;

import com.example.dashboardapi.model.ExerciseSet;
import com.example.dashboardapi.repository.ExerciseSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;



@RestController
@RequestMapping("api/set")
public class ExerciseSetController {

  @Autowired
  ExerciseSetRepository exerciseSetRepository;

  @GetMapping
  public List<ExerciseSet> getExerciseSetsBySessionId(@RequestParam("session_id") UUID sessionId) {
    return exerciseSetRepository.findBySessionId(sessionId);
  }
}
