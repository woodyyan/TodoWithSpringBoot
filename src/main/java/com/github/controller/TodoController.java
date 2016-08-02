package com.github.controller;

import com.github.model.TodoItem;
import com.github.repository.TodoRepository;
import com.github.repository.TodoRepositoryImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@RestController
@RequestMapping("/items")
public class TodoController {
    private TodoRepository todoItems;

    public TodoController() {
        todoItems = new TodoRepositoryImpl();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<TodoItem>> getAllTodos() {
        Collection<TodoItem> items = todoItems.getAll();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @RequestMapping("/{key}")
    public ResponseEntity<TodoItem> getByKey(@PathVariable("key") String key) {
        TodoItem item = todoItems.find(key);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody TodoItem item) {
        todoItems.add(item);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{key}")
    public ResponseEntity update(@PathVariable("key") String key, @RequestBody TodoItem item) {
        if (item == null || !item.getKey().equals(key)) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        TodoItem todo = todoItems.find(key);
        todoItems.update(item);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{key}")
    public ResponseEntity delete(@PathVariable("key") String key) {
        todoItems.remove(key);
        return new ResponseEntity(HttpStatus.OK);
    }
}
