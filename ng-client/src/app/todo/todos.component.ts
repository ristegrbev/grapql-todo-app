import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from "rxjs";
import { TodosService } from "./todos.service";

@Component({
  selector: 'todos',
  styleUrls: ['./todos.component.scss'],
  templateUrl: './todos.component.html'
})
export class TodosComponent implements OnInit, OnDestroy {
  title: string;

  loading: boolean;
  todos: Array<any> = [];
  filtered: Array<any> = [];

  private todos$: Subscription;

  constructor(private service: TodosService) {
  }

  ngOnInit(): void {
    this.findAllTodos();

  }

  ngOnDestroy(): void {
    this.todos$.unsubscribe();
  }

  findAllTodos() {
    this.todos$ = this.service.findAllTodos().subscribe(({data, loading}) => {
      this.loading = loading;
      this.todos = this.filtered = data.todos;
    });
  }

  updateCompletionStatus(todo: any) {
    this.service.updateCompletionStatus(todo.id, todo.completed).subscribe();
  }

  createTodo() {
    if (!this.title) {
      return;
    }
    return this.service.createTodo(this.title).subscribe((response: any) => {
      this.title = '';
      this.todos.push(response.data.saveTodo);
      this.filtered = this.todos;
    });
  }

  filterTodos() {
    if (!this.title) {
      this.filtered = this.todos;
      return;
    }
    this.filtered = this.todos.filter(todo => todo.title.toLowerCase().includes(this.title.toLowerCase()));
  }

  todosContainsTitle() {
    return this.todos.filter(todo => todo.title.toLowerCase() === this.title.toLowerCase() && !todo.completed).length > 0;
  }
}
