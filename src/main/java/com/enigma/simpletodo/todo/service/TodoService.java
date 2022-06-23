package com.enigma.simpletodo.todo.service;

import com.enigma.simpletodo.todo.model.TodoRequest;
import com.enigma.simpletodo.todo.model.TodoResponse;

import java.util.List;

public interface TodoService {

    TodoResponse create(TodoRequest request);

    TodoResponse get(String id);

    List<TodoResponse> findAll();

    TodoResponse update(TodoRequest request);

    void delete(String id);

}
