package com.example.dashboardapi.controller;

import com.example.dashboardapi.model.Weight;
import com.example.dashboardapi.repository.WeightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/weight")
public class WeightController {

    private final WeightRepository weightRepository;

    @Autowired
    public WeightController(WeightRepository weightRepository) {
        this.weightRepository = weightRepository;
    }

    @PostMapping
    public ResponseEntity<Weight> createWeight(@RequestBody Weight weight) {
        Weight savedWeight = weightRepository.save(weight);
        return new ResponseEntity<>(savedWeight, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Weight>> getAllWeights() {
        List<Weight> weights = weightRepository.findAll();

        return ResponseEntity.ok(weights);
    }

    @GetMapping("/user")
    public ResponseEntity<List<Weight>> getAllUserWeights(@RequestParam UUID userId) {
        List<Weight> usersWeights = weightRepository.findByUserId(userId);
        return ResponseEntity.ok(usersWeights);
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteWeight(@RequestParam UUID id) {
        weightRepository.deleteById(id);
        return new ResponseEntity<>("Deleted Weight with id:" + id, HttpStatus.OK);
    }
}
