package com.enigma.simpletodo.todo.entity;

import com.enigma.simpletodo.todo.model.TodoResponse;
import com.enigma.simpletodo.utils.Utils;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "todos")
public class Todo {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    private String name;

    private Boolean isCompleted;

    private Long createdAt;

    public Todo(String id, String name, Boolean isCompleted, Long createdAt) {
        this.id = id;
        this.name = name;
        this.isCompleted = isCompleted;
        this.createdAt = createdAt;
    }

    public Todo(String id, String name, Boolean isCompleted) {
        this.id = id;
        this.name = name;
        this.isCompleted = isCompleted;
    }

    public Todo(String name, Boolean isCompleted) {
        this.name = name;
        this.isCompleted = isCompleted;
    }

    public Todo() {
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

    public Long getCreatedAt() {
        return createdAt;
    }

    @PrePersist
    protected void onPrePersist() {
        if (createdAt == null) createdAt = System.currentTimeMillis();
    }

    public TodoResponse toTodoResponse() {
        Utils utils = new Utils();
        return new TodoResponse(id, name, isCompleted, utils.convertEpochTimeToDate(createdAt));
    }

}
