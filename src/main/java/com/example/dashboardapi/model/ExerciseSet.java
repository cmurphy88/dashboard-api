package com.example.dashboardapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "exercise_set")
public class ExerciseSet {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(updatable = false, nullable = false)
  private UUID id;

  @Column(name = "\"order\"")
  private Integer order;

  @Column(name = "weight")
  private Double weight;

  @Column(name = "reps")
  private Integer reps;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "exercise_id", nullable = false)
  @JsonIgnore
  private Exercise exercise;

}