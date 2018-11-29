import { Injectable } from '@angular/core';
import { Apollo } from "apollo-angular";
import { Observable } from "rxjs";
import { ApolloQueryResult } from "apollo-client";
import gql from "graphql-tag";

@Injectable({
  providedIn: 'root'
})
export class TodosService {

  private FindAllTodos = gql`
    query findAllTodos {
      todos {
        id
        title
        completed
      }
    }
  `;

  constructor(private apollo: Apollo) {
  }

  findAllTodos(): Observable<ApolloQueryResult<any>> {
    return this.apollo.watchQuery<any>({
      query: this.FindAllTodos
    }).valueChanges;
  }

  updateCompletionStatus(id: number, completed: boolean) {
    return this.apollo.mutate({
      mutation: gql`mutation updateCompletionStatus {
        updateCompletionStatus(id: ${id}, completed: ${completed}) {
          id
        }
      }`
    });
  }

  createTodo(title: string) {
    return this.apollo.mutate({
      mutation: gql`mutation create {
        saveTodo(todo: {title: "${title}", completed: false}) {
          id
          title
          completed
        }
      }`
    });
  }
}
