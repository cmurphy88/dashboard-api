package com.example.dashboardapi.controller;

import com.example.dashboardapi.dto.*;
import com.example.dashboardapi.model.Exercise;
import com.example.dashboardapi.model.ExerciseSet;
import com.example.dashboardapi.model.Session;
import com.example.dashboardapi.repository.ExerciseRepository;
import com.example.dashboardapi.repository.ExerciseSetRepository;
import com.example.dashboardapi.repository.SessionRepository;
import com.example.dashboardapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("api/session")
public class SessionController {

  @Autowired
  private SessionRepository sessionRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ExerciseSetRepository exerciseSetRepository;

  @Autowired
  private ExerciseRepository exerciseRepository;

  @PostMapping
  public ResponseEntity<?> createNewSession(@RequestBody SessionRequestDTO sessionRequestDTO) {
    Session session = new Session();
    session.setName(sessionRequestDTO.getName());
    session.setUserId(sessionRequestDTO.getUserId());
    session.setDate(sessionRequestDTO.getDate());

    Session savedSession = sessionRepository.save(session);

    List<ExerciseDTO> exerciseDTOs = sessionRequestDTO.getExercises();
    for (ExerciseDTO exerciseDTO : exerciseDTOs) {

      Exercise exercise = new Exercise();
      exercise.setName(exerciseDTO.getName());

      // does exercise exist
      UUID exerciseId = null;
      if (exerciseDTO.getId() == null) {
        exerciseId = UUID.randomUUID();
      } else {
        exerciseId = exerciseDTO.getId();
      }
      boolean doesExerciseExist = exerciseRepository.existsById(exerciseId);
      if (!doesExerciseExist) {
        Exercise savedExercise = exerciseRepository.save(exercise);
        exercise.setId(savedExercise.getId());
      } else {
        exercise.setId(exerciseDTO.getId());
      }

      for (SetDTO setDTO : exerciseDTO.getSets()) {
        ExerciseSet exerciseSet = new ExerciseSet();
        exerciseSet.setOrder(setDTO.getOrder());
        exerciseSet.setWeight(setDTO.getWeight());
        exerciseSet.setReps(setDTO.getReps());
        exerciseSet.setExerciseId(exercise.getId());
        exerciseSet.setSessionId(savedSession.getId());
        exerciseSetRepository.save(exerciseSet);
      }
    }
    return ResponseEntity.ok(savedSession);
  }

  @GetMapping("/user")
  public List<SessionResponseDTO> getAllUsersSessions(@RequestParam UUID userId) {
    List<Session> userSessions = sessionRepository.findAllByUserId(userId);
    return buildSessionResponses(userSessions);
  }

  private SessionResponseDTO buildSessionResponse(Session session) {
    SessionResponseDTO sessionResponseDTO = new SessionResponseDTO();
    sessionResponseDTO.setId(session.getId());
    sessionResponseDTO.setName(session.getName());
    sessionResponseDTO.setDate(session.getDate());
    sessionResponseDTO.setUserId(session.getUserId());

    List<ExerciseSet> sessionSets = exerciseSetRepository.findBySessionId(session.getId());

    List<SetResponseDTO> setResponseDTOs = new ArrayList<>();
    for (ExerciseSet set : sessionSets) {
      SetResponseDTO setResponseDTO = new SetResponseDTO();
      setResponseDTO.setId(set.getId());
      setResponseDTO.setOrder(set.getOrder());
      setResponseDTO.setWeight(set.getWeight());
      setResponseDTO.setReps(set.getReps());
      setResponseDTO.setExercise_id(set.getExerciseId());
      setResponseDTOs.add(setResponseDTO);
    }

    List<ExerciseSetResponseDTO> exerciseResponse = new ArrayList<>();
    Map<String, List<SetResponseDTO>> exerciseSetMap = new HashMap<>();

    for (SetResponseDTO setResponseDTO : setResponseDTOs) {
      UUID exerciseId = setResponseDTO.getExercise_id();
      String exerciseName = exerciseRepository.findExerciseById(exerciseId).getName();
      if (!exerciseSetMap.containsKey(exerciseName + "|" + exerciseId)) {
        exerciseSetMap.put(exerciseName + "|" + exerciseId, new ArrayList<>());
        exerciseSetMap.get(exerciseName + "|" + exerciseId).add(setResponseDTO);
      } else {
        exerciseSetMap.get(exerciseName + "|" + exerciseId).add(setResponseDTO);
      }
    }

    for (Map.Entry<String, List<SetResponseDTO>> entry : exerciseSetMap.entrySet()) {
      ExerciseSetResponseDTO exerciseSetResponseDTO = new ExerciseSetResponseDTO();
      String[] exerciseNameAndId = entry.getKey().split("\\|");
      List<SetResponseDTO> setResponseDTOList = entry.getValue();
      exerciseSetResponseDTO.setExercise_name(exerciseNameAndId[0]);
      exerciseSetResponseDTO.setExercise_id(UUID.fromString(exerciseNameAndId[1]));
      exerciseSetResponseDTO.setSets(setResponseDTOList);
      exerciseResponse.add(exerciseSetResponseDTO);
    }

    sessionResponseDTO.setExercises(exerciseResponse);
    return sessionResponseDTO;
  }

  private List<SessionResponseDTO> buildSessionResponses(List<Session> userSessions) {
    List<SessionResponseDTO> sessionResponseDTOs = new ArrayList<>();
    for (Session session : userSessions) {
      SessionResponseDTO sessionResponseDTO = buildSessionResponse(session);
      sessionResponseDTOs.add(sessionResponseDTO);
    }
    return sessionResponseDTOs;
  }
}