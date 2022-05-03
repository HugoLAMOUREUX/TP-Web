package web.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class TodoList {
    private String name;
    private List<Todo> todoList;

    public Todo findTodo(String todoTitle){
        for (Todo todo : todoList){
            if(todo.getTitle().equals(todoTitle)){
                return todo;
            }
        }
        return null;
    }
}
