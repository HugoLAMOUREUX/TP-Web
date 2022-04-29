package web.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import web.model.Category;
import web.model.Todo;
import web.model.TodoList;
import web.model.User;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/insa/v1/todo")
public class TodoV1 {

    private List<User> users=new ArrayList<User>();


    @GetMapping(path="hello",produces= MediaType.TEXT_PLAIN_VALUE)
    public String Q12(){
        return "hello bitch";
    }
    @GetMapping(path="todo",produces=MediaType.APPLICATION_JSON_VALUE)
    public TodoList Q14(){
        TodoList res=new TodoList();
        //Todo td1= new Todo("la liste de lolo","probablement faire du ping pong","battre philipe gall",Category.HIGH_PRIORITY);
        //ires.setTodoList(List.of(td1));
        return res;
    }

    @PostMapping(path="todo",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void q15(@RequestBody final String txt){
        System.out.println(txt);
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
