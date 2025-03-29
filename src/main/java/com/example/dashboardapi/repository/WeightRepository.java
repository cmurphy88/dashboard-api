package com.example.dashboardapi.repository;

import com.example.dashboardapi.model.Weight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface WeightRepository extends JpaRepository<Weight, UUID> {
    List<Weight> findByUserId(UUID userId);
}
