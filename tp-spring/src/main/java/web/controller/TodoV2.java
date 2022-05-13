package web.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import web.model.Category;
import web.model.Todo;
import web.model.TodoList;
import web.model.User;
import web.service.TodoService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/public/insa/v2/todo")
public class TodoV2 {
    private final TodoService todoService;



    public TodoV2(final TodoService todoService){
        super();
        this.todoService = todoService;
    }

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

        if(todoService.findTodo(userName,todolistName,todo.getTitle()) == null){
            for ( User u : todoService.getUsers()){
                if(u.getName().equals(userName)){
                    try {
                        TodoList res = u.findTodoList(todolistName);
                        List<Todo> res2 = res.getTodoList();
                        res2.add(todo);
                        res.setTodoList(res2);
                        System.out.println(u.findTodoList(todolistName));
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



    @PostMapping(path="user",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void q16(@RequestBody final User user){
        for(User u :  todoService.getUsers()){
            if(u.getName().equals(user.getName())){

                return;
            }

        }

        List<User> list = todoService.getUsers();
        list.add(user);
        todoService.setUsers(list);
        System.out.println(list);
    }

    @PatchMapping(path="user/{name}",consumes=MediaType.APPLICATION_JSON_VALUE)
    public void modif(@RequestBody final User user,@PathVariable("name") String name){

        for(User u :  todoService.getUsers()) {
            if (u.getName().equals(name)) {
                List<User> users = todoService.getUsers();
                users.remove(u);
                users.add(user);
                todoService.setUsers(users);
                System.out.println(users);
            }
        }

    }



    @DeleteMapping(path="user/{name}")
    public void delete(@PathVariable("name") String name){
        for(User u :  todoService.getUsers()) {
            if (u.getName().equals(name)) {
                List<User> users = todoService.getUsers();
                users.remove(u);
                todoService.setUsers(users);
                System.out.println(users);

            }
        }
    }

    @PostMapping(path="todolist/{username}",consumes=MediaType.APPLICATION_JSON_VALUE)
    public void addList(@RequestBody TodoList todo, @PathVariable String username){
        for(User u :  todoService.getUsers()) {
            if (u.getName().equals(username)) {
                List<TodoList> temp=u.getLists();
                temp.add(todo);
                u.setLists(temp);
                System.out.println(u.getLists());
            }
        }


    }
}
