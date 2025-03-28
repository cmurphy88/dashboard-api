package com.example.dashboardapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.dashboardapi.model.Todo;
import com.example.dashboardapi.repository.TodoRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoRepository todoRepository;

    @Autowired
    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodos() {
        List<Todo> todos = todoRepository.findAll();

        return ResponseEntity.ok(todos);
    }

    @PostMapping
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo) {
        if (todo.getCompleted() == null) {
            todo.setCompleted(false);
        }
        Todo savedTodo = todoRepository.save(todo);
        return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable UUID id, @RequestBody Todo updatedTodoData) {

        Optional<Todo> optionalTodo = todoRepository.findById(id);

        if (optionalTodo.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Todo existingTodo = optionalTodo.get();
        existingTodo.setContent(updatedTodoData.getContent());
        existingTodo.setDueDate(updatedTodoData.getDueDate());
        existingTodo.setCompleted(updatedTodoData.getCompleted());
        Todo savedTodo = todoRepository.save(existingTodo);

        return ResponseEntity.ok(savedTodo);
    }
}
