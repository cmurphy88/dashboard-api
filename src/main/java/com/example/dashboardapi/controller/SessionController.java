package com.example.dashboardapi.controller;

import com.example.dashboardapi.dto.ExerciseDTO;
import com.example.dashboardapi.dto.SessionRequestDTO; // Import DTO
import com.example.dashboardapi.dto.SetDTO;
import com.example.dashboardapi.model.Exercise;
import com.example.dashboardapi.model.ExerciseSet; // Or ExerciseSet if you renamed it
import com.example.dashboardapi.model.Session;
import com.example.dashboardapi.model.User; // Import User entity
import com.example.dashboardapi.repository.SessionRepository;
import com.example.dashboardapi.repository.UserRepository; // Import UserRepository
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/session")
public class SessionController {

  @Autowired
  private SessionRepository sessionRepository;

  @Autowired
  private UserRepository userRepository;

  @GetMapping
  public ResponseEntity<List<Session>> getAllUserSessions(@RequestParam UUID userId) {
    List<Session> sessions = sessionRepository.findByUserIdOrderByDateAsc(userId);
    return ResponseEntity.ok(sessions);
  }


  @PostMapping
  public ResponseEntity<Session> createSession(@RequestBody SessionRequestDTO sessionDto) {
    User user = userRepository.findById(sessionDto.getUserId())
        .orElseThrow(() -> new Error("User not found with id: " + sessionDto.getUserId()));

    Session newSession = new Session();
    newSession.setName(sessionDto.getName());
    newSession.setUser(user);
    newSession.setDate(sessionDto.getDate());

    if (sessionDto.getExercises() != null) {
      for (ExerciseDTO exerciseDto : sessionDto.getExercises()) {
        Exercise newExercise = new Exercise();
        newExercise.setName(exerciseDto.getName());
        newSession.addExercise(newExercise);

        if (exerciseDto.getSets() != null) {
          for (SetDTO setDto : exerciseDto.getSets()) {
            ExerciseSet newSet = new ExerciseSet();
            newSet.setOrder(setDto.getOrder());
            newSet.setWeight(setDto.getWeight());
            newSet.setReps(setDto.getReps());
            newExercise.addSet(newSet);
          }
        }
      }
    }

    Session savedSession = sessionRepository.save(newSession);

    return new ResponseEntity<>(savedSession, HttpStatus.CREATED);
  }
}