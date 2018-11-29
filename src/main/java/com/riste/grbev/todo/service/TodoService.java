package com.riste.grbev.todo.service;

import com.riste.grbev.todo.domain.Todo;

import java.util.List;

public interface TodoService {
    List<Todo> findAll();

    Todo findOne(Long id) throws Exception;

    Todo save(Todo todo);

    void delete(Long id);

    Todo updateCompletionStatus(Long id, boolean completed) throws Exception;
}
