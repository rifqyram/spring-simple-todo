package com.enigma.simpletodo.todo.controller;

import com.enigma.simpletodo.todo.model.TodoRequest;
import com.enigma.simpletodo.todo.model.TodoResponse;
import com.enigma.simpletodo.todo.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.enigma.simpletodo.utils.WebResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todos")
@SecurityRequirement(name = "Authorization")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:4200"})
@Tag(name = "Todo")
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<WebResponse<?>> createTodo(@RequestBody TodoRequest request) {
        TodoResponse todoResponse = todoService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new WebResponse<>(
                        HttpStatus.CREATED.value(),
                        HttpStatus.CREATED,
                        "Successfully create todo",
                        todoResponse
                ));
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<WebResponse<?>> getTodoById(@PathVariable String id) {
        TodoResponse todoResponse = todoService.get(id);
        return ResponseEntity.ok()
                .body(new WebResponse<>(
                        HttpStatus.OK.value(),
                        HttpStatus.OK,
                        "Successfully get todo",
                        todoResponse
                ));
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<WebResponse<?>> getTodos() {
        List<TodoResponse> todoResponses = todoService.findAll();
        return ResponseEntity.ok()
                .body(new WebResponse<>(
                        HttpStatus.OK.value(),
                        HttpStatus.OK,
                        "Successfully get all todo",
                        todoResponses
                ));
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<WebResponse<?>> updateTodo(@RequestBody TodoRequest request) {
        TodoResponse todoResponse = todoService.update(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new WebResponse<>(
                        HttpStatus.NO_CONTENT.value(),
                        HttpStatus.NO_CONTENT,
                        "Successfully update todo",
                        todoResponse
                ));
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<WebResponse<?>> deleteTodo(@PathVariable String id) {
        todoService.delete(id);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new WebResponse<>(
                        HttpStatus.NO_CONTENT.value(),
                        HttpStatus.NO_CONTENT,
                        "Successfully delete todo",
                        id
                ));
    }
}
