package com.riste.grbev.todo.domain;

import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
@ToString @EqualsAndHashCode
public class Todo {

    @Id
    @GeneratedValue
    @GraphQLQuery(name = "id", description = "The ID of a Todo")
    private Long id;

    @GraphQLQuery(name = "title", description = "The title of a Todo")
    private @NonNull String title;

    @GraphQLQuery(name = "completed", description = "Shows that a Todo's is completed")
    @Setter
    private boolean completed;

    public Todo(String title) {
        this.title = title;
        this.completed = false;
    }
}
