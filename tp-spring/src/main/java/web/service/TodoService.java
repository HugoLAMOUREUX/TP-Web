package web.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import web.model.Todo;
import web.model.TodoList;
import web.model.User;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Service
public class TodoService {
    private List<User> users=new ArrayList<User>();

    public User getUser(String letsgo){
        for(User e : users){
            if(e.getName().equals(letsgo)){
                return e;
            }
        }

        return null;
    }

    public void addUser(User u){
        if(u!= null){
        users.add(u);}
    }


    public Todo findTodo (String userName, String todolistName, String todoName){
        for ( User u : users){
            if(u.getName().equals(userName)){
                for (TodoList todoList : u.getLists()){
                    if(todoList.getName().equals(todolistName)){
                        for (Todo todo : todoList.getTodoList()){
                            if(todo.getTitle().equals(todoName)){
                                return todo;
                            }
                        }
                    }
                }
            }
        }
        return null;

    }


}
