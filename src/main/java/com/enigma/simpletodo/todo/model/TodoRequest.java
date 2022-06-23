package com.enigma.simpletodo.todo.model;

import com.enigma.simpletodo.todo.entity.Todo;

public class TodoRequest {

    private String id;

    private String name;

    private Boolean isCompleted;

    public TodoRequest(String id, String name, Boolean isCompleted) {
        this.id = id;
        this.name = name;
        this.isCompleted = isCompleted;
    }

    public TodoRequest() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Boolean getIsCompleted() {
        return isCompleted;
    }

    public Todo toTodo() {
        return new Todo(id, name, isCompleted);
    }
}
