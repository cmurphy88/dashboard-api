package com.example.dashboardapi.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "todo")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private UUID id;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "due_date")
    private LocalDateTime dueDate;

    @Column(name = "content")
    private String content;

    @Column(name = "completed")
    private Boolean completed = false;

    public Todo() {
    }

    public UUID getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public String getContent() {
        return content;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
}
