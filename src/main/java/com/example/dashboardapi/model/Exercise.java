package com.example.dashboardapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "exercise")
public class Exercise {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(updatable = false, nullable = false)
  private UUID id;

  @Column(name = "name")
  private String name;

  @OneToMany(
      mappedBy = "exercise",
      cascade = CascadeType.ALL,
      orphanRemoval = true,
      fetch = FetchType.LAZY
  )
  private List<ExerciseSet> sets = new ArrayList<>();


  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "session_id")
  @JsonIgnore
  private Session session;

  public void addSet(ExerciseSet set) {
    this.sets.add(set);
    set.setExercise(this);
  }

  public void removeSet(ExerciseSet set) {
    this.sets.remove(set);
    set.setExercise(null);
  }

}