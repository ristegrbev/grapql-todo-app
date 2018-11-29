package com.riste.grbev.todo;

import com.riste.grbev.todo.domain.Todo;
import com.riste.grbev.todo.service.TodoService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class ToDoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToDoApplication.class, args);
    }

    @Bean
    ApplicationRunner init(TodoService service) {
        return args -> {
            Stream.of("Buy milk", "Continue reading the book", "Book flight").forEach(title -> {
                Todo todo = new Todo(title);
                service.save(todo);
            });
            service.findAll();
        };
    }
}
