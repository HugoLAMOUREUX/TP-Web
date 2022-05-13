package web.controller;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import web.model.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/public/insa/v1/todo")
public class TodoV1 {

    private List<User> users=new ArrayList<User>();


    @GetMapping(path="hello",produces= MediaType.TEXT_PLAIN_VALUE)
    public String Q12(){
        return "hello bitch";
    }
    @GetMapping(path="todo",produces=MediaType.APPLICATION_JSON_VALUE)
    public TodoList get(){
        TodoList res=new TodoList();
        //Todo td1= new Todo("la liste de lolo","probablement faire du ping pong","battre philipe gall",Category.HIGH_PRIORITY);
        //res.setTodoList(List.of(td1));
        return res;
    }


    @PostMapping(path="todo/{userName}/{todolistName}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void q15(@RequestBody final Todo todo,@PathVariable("userName") final String userName, @PathVariable("todolistName") final String todolistName){

        if(findTodo(userName,todolistName,todo.getTitle()) == null){
            for ( User u : users){
                if(u.getName().equals(userName)){
                    try {
                        TodoList res = u.findTodoList(todolistName);
                        List<Todo> res2 = res.getTodoList();
                        res2.add(todo);
                        res.setTodoList(res2);
                        System.out.println(u.findTodoList(todolistName));
                        System.out.println(res2);
                    }catch(NullPointerException e){
                        e.printStackTrace();
                    }
                }
            }
        }else {
            System.out.println("deja present mon reuf");
        }
        System.out.println("lets gooo");
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

    @PostMapping(path="user",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void q16(@RequestBody final User user){
        for(User u :  users){
            if(u.getName().equals(user.getName())){

                return;
            }

        }

        users.add(user);
        System.out.println(users);
    }

    @PatchMapping(path="user/{name}",consumes=MediaType.APPLICATION_JSON_VALUE)
    public void modif(@RequestBody final User user,@PathVariable("name") String name){
        for(User u :  users) {
            if (u.getName().equals(name)) {
                users.remove(u);
                users.add(user);
            }
        }
        System.out.println(users);
    }



    @DeleteMapping(path="user/{name}")
    public void delete(@PathVariable("name") String name){
        for(User u :  users) {
            if (u.getName().equals(name)) {
                users.remove(u);
            }
        }
        System.out.println(users);
    }

    @PostMapping(path="todolist/{username}",consumes=MediaType.APPLICATION_JSON_VALUE)
    public void addList(@RequestBody TodoList todo, @PathVariable String username){
        for(User u :  users) {
            if (u.getName().equals(username)) {
                List<TodoList> temp=u.getLists();
                temp.add(todo);
                u.setLists(temp);
            }
        }
        System.out.println(users);

    }
}
