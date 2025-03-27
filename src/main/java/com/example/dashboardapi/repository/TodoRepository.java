package com.example.dashboardapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;
import com.example.dashboardapi.model.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, UUID> {
}
