package com.example.security.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TodoController {
        private static final Logger logger = LoggerFactory.getLogger(TodoController.class);
        private static final List<Todo> todoList = new ArrayList<>(List.of(
                new Todo("walk", "walking in the park"),
                new Todo("sing", "singing on the stage"),
                new Todo("eat", "eating in a restaurant")
        ));

        @GetMapping("/todos")
        public List<Todo> getTodoList() {
                return todoList;
        }

        @GetMapping("/users/{username}/todos")
        public List<Todo> getTodoListByUsername(@PathVariable String username) {
                List<Todo> userTodos = new ArrayList<>();
                for (Todo todo : todoList) {
                        if (todo.username().equalsIgnoreCase(username)) {
                                userTodos.add(todo);
                        }
                }
                return userTodos;
        }

        @PostMapping("/users/{username}/todos")
        public void addTodo(@PathVariable String username, @RequestBody Todo todo) {
                Todo newTodo = new Todo(username, todo.description());
                todoList.add(newTodo);
                logger.info("Created todo '{}' for user '{}'", todo.description(), username);
        }
}

record Todo(String username, String description) {}
