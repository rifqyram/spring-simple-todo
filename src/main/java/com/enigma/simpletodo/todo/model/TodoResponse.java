package com.enigma.simpletodo.todo.model;

import java.util.Date;

public class TodoResponse {

    private String id;

    private String name;

    private Boolean isCompleted;

    private String createdAt;

    public TodoResponse(String id, String name, Boolean isCompleted, String createdAt) {
        this.id = id;
        this.name = name;
        this.isCompleted = isCompleted;
        this.createdAt = createdAt;
    }

    public TodoResponse() {
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

    public String getCreatedAt() {
        return createdAt;
    }

}
