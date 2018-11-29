package com.riste.grbev.todo.service.impl;

import com.riste.grbev.todo.domain.Todo;
import com.riste.grbev.todo.repository.TodoRepository;
import com.riste.grbev.todo.service.TodoService;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.annotations.GraphQLSubscription;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {

    private final TodoRepository repository;

    public TodoServiceImpl(TodoRepository repository) {
        this.repository = repository;
    }

    @Override
    @GraphQLQuery(name = "todos")
    public List<Todo> findAll() {
        return repository.findAll();
    }

    @Override
    @GraphQLQuery(name = "todo")
    public Todo findOne(@GraphQLArgument(name = "id") Long id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception("Todo not found"));
    }

    @Override
    @GraphQLMutation(name = "saveTodo")
    public Todo save(@GraphQLArgument(name = "todo") Todo todo) {
        return repository.save(todo);
    }

    @Override
    @GraphQLMutation(name = "updateCompletionStatus")
    public Todo updateCompletionStatus(@GraphQLArgument(name = "id") Long id,
                                       @GraphQLArgument(name = "completed") boolean completed) throws Exception {
        if (id == null) {
            throw new Exception("Id param required");
        }
        Todo todo = repository.findById(id).orElseThrow(() -> new Exception("Todo not found"));
        todo.setCompleted(completed);
        return repository.save(todo);
    }

    @Override
    @GraphQLMutation(name = "deleteTodo")
    public void delete(@GraphQLArgument(name = "id") Long id) {
        repository.deleteById(id);
    }
}
