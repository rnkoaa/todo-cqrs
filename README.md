```yaml
AggregateRoot
  Todo:
    commands:
      AddTodo
      CompleteTodo
      showSince
      editTodo
      removeCompleted
    events:
      onTodoAdded
      onTodoCompleted
      onTitleUpdated
      onCompletedTodosRemoved
```

https://www.thoughts-on-java.org/persist-postgresqls-jsonb-data-type-hibernate/
http://www.baeldung.com/jackson-inheritance