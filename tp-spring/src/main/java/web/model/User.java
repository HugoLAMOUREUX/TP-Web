package web.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class User {
    private String name;
    private List<TodoList> lists;

    public TodoList findTodoList(String todoListName) {
        for ( TodoList todoList : lists){
            if(todoList.getName().equals(todoListName)){
                return todoList;
            }
        }
        return null;
    }
}
