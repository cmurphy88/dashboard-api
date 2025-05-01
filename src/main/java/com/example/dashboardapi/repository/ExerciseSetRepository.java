package com.example.dashboardapi.repository;

import com.example.dashboardapi.model.ExerciseSet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ExerciseSetRepository extends JpaRepository<ExerciseSet, UUID> {
  List<ExerciseSet> findBySessionId(UUID sessionId);
}
