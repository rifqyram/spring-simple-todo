package com.enigma.simpletodo.todo.service.impl;

import com.enigma.simpletodo.exception.NotFoundException;
import com.enigma.simpletodo.todo.entity.Todo;
import com.enigma.simpletodo.todo.model.TodoRequest;
import com.enigma.simpletodo.todo.model.TodoResponse;
import com.enigma.simpletodo.todo.repository.TodoRepository;
import com.enigma.simpletodo.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    @Autowired
    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public TodoResponse create(TodoRequest request) {
        Todo todo = todoRepository.save(request.toTodo());
        return todo.toTodoResponse();
    }

    @Override
    public void delete(String id) {
        Todo todo = findByIdOrThrowNotFound(id);
        todoRepository.delete(todo);
    }

    @Override
    public TodoResponse get(String id) {
        return findByIdOrThrowNotFound(id).toTodoResponse();
    }

    @Override
    public List<TodoResponse> findAll() {
        return todoRepository.findAll().stream().map(Todo::toTodoResponse).collect(Collectors.toList());
    }

    @Override
    public TodoResponse update(TodoRequest request) {
        Todo currentTodo = findByIdOrThrowNotFound(request.getId());
        Todo todo = new Todo(request.getId(), request.getName(), request.getIsCompleted(), currentTodo.getCreatedAt());
        return todoRepository.save(todo).toTodoResponse();
    }

    private Todo findByIdOrThrowNotFound(String id) {
        return todoRepository.findById(id).orElseThrow(() -> new NotFoundException("Todo not found"));
    }
}
