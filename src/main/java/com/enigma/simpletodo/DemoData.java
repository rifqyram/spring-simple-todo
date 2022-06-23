package com.enigma.simpletodo;

import com.enigma.simpletodo.todo.entity.Todo;
import com.enigma.simpletodo.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DemoData {

    private final TodoRepository todoRepository;

    @Autowired
    public DemoData(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @EventListener
    public void appReady(ApplicationReadyEvent event) {
        List<Todo> todos = Arrays.asList(
                new Todo("Makan", false),
                new Todo("Tidur", true),
                new Todo("Coding", true),
                new Todo("Main", false),
                new Todo("Bejalar", true)
        );
        todoRepository.saveAll(todos);
    }

}
