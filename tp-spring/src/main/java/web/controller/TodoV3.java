package web.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.model.UserDTO;
import web.service.TodoService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/public/insa/v3/todo")
public class TodoV3 {
    private final TodoService todoService;

    public TodoV3(TodoService todoService) {
        this.todoService = todoService;
    }


    @GetMapping(path="{user}/getall",produces= MediaType.APPLICATION_JSON_VALUE)
    public User Q12(@PathVariable("user") final String userName){
        return todoService.getUser(userName);
    }

    @GetMapping(path="{user}/getlist",produces= MediaType.APPLICATION_JSON_VALUE)
    public UserDTO Q13(@PathVariable("user") final String userName){
        return new UserDTO(todoService.getUser(userName));
    }




    @PostMapping(path="user",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void q16(@RequestBody final User user){
        User a = todoService.getUser(user.getName());
        if( a == null) {
            todoService.addUser(user);
        }

        System.out.println(user.getName());
    }

    @PatchMapping(path="user",consumes=MediaType.APPLICATION_JSON_VALUE)
    public void modif(@RequestBody final User user){
                List<User> a = todoService.getUsers();
                for(User l : a){
                    if(l.getName().equals(user.getName())){
                        a.remove(l);
                    }
                }
                a.add(user);
                todoService.setUsers(a);
                System.out.println(a);



    }


}
