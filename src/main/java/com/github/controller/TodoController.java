package com.github.controller;

import com.github.model.TodoItem;
import com.github.repository.TodoRepository;
import com.github.repository.TodoRepositoryImpl;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@RestController
@RequestMapping("/todo")
public class TodoController {
    private TodoRepository todoItems;

    public TodoController() {
        todoItems = new TodoRepositoryImpl();
    }

    @RequestMapping("/all")
    public Collection<TodoItem> getAllTodos() {
        return todoItems.getAll();
    }

    @RequestMapping("/{key}")
    public TodoItem getByKey(@PathVariable("key") String key) {

        return todoItems.find(key);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public String create(@RequestBody TodoItem item) {
        todoItems.add(item);
        return "Create successful!";
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update/{key}")
    public String update(@PathVariable("key") String key, @RequestBody TodoItem item) {
        if (item == null || !item.getKey().equals(key))
        {
            return "error";
        }

        TodoItem todo = todoItems.find(key);
        todoItems.update(item);
        return "update successful";
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{key}")
    public String delete(@PathVariable("key") String key) {
        todoItems.remove(key);
        return "delete successful";
    }
}
