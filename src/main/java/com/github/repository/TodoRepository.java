package com.github.repository;


import com.github.model.TodoItem;

import java.util.Collection;

public interface TodoRepository {
    void add(TodoItem item);
    Collection<TodoItem> getAll();
    TodoItem find(String key);
    TodoItem remove(String key);
    void update(TodoItem item);
}
