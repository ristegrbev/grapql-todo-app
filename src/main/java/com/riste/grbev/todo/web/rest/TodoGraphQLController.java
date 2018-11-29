package com.riste.grbev.todo.web.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.riste.grbev.todo.service.TodoService;
import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import io.leangen.graphql.GraphQLSchemaGenerator;
import io.leangen.graphql.metadata.strategy.query.AnnotatedResolverBuilder;
import io.leangen.graphql.metadata.strategy.value.jackson.JacksonValueMapperFactory;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/todos")
public class TodoGraphQLController {

    private static final String QUERY = "query";
    private static final String OPERATION_NAME = "operationName";

    private GraphQL graphQL;

    public TodoGraphQLController(TodoService service) {
        GraphQLSchema schema = new GraphQLSchemaGenerator()
                .withResolverBuilders(new AnnotatedResolverBuilder())
                .withOperationsFromSingleton(service)
                .withValueMapperFactory(new JacksonValueMapperFactory())
                .generate();
        graphQL = GraphQL.newGraphQL(schema).build();
    }

    @PostMapping(value = "/_graphql", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Map<String, Object> post(@RequestBody Map<String, Object> request, HttpServletRequest raw) {
        String query = request.get(QUERY).toString();
        String operationName = request.get(OPERATION_NAME) != null ? request.get(OPERATION_NAME).toString() : null;
        return graphQL.execute(
                ExecutionInput
                        .newExecutionInput()
                        .query(query)
                        .operationName(operationName)
                        .context(raw)
                        .build()
        ).toSpecification();
    }
}
