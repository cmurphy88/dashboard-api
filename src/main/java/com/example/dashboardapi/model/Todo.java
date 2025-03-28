package com.example.dashboardapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "todo")
public class Todo {

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private UUID id;

    @Setter
    @Getter
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Setter
    @Getter
    @Column(name = "due_date")
    private LocalDateTime dueDate;

    @Setter
    @Getter
    @Column(name = "content")
    private String content;

    @Getter
    @Setter
    @Column(name = "completed")
    private Boolean completed = false;

    @Getter
    @Setter
    @Column(name = "user_id")
    private UUID userId;

    public Todo() {
    }

}
