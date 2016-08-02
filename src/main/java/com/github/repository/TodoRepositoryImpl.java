package com.github.repository;

import com.github.model.TodoItem;

import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

public class TodoRepositoryImpl implements TodoRepository {
    private static HashMap<String, TodoItem> todos = new HashMap<>();
    private final AtomicLong counter = new AtomicLong();

    public TodoRepositoryImpl() {
        TodoItem item = new TodoItem();
        item.setKey(Long.toString(counter.incrementAndGet()));
        item.setName("First todo");
        add(item);
    }

    @Override
    public void add(TodoItem item) {
        item.setKey(Long.toString(counter.incrementAndGet()));
        todos.put(item.getKey(), item);
    }

    @Override
    public Collection<TodoItem> getAll() {
        return todos.values();
    }

    @Override
    public TodoItem find(String key) {
        return todos.get(key);
    }

    @Override
    public TodoItem remove(String key) {
        return todos.remove(key);
    }

    @Override
    public void update(TodoItem item) {
        todos.put(item.getKey(), item);
    }
}
