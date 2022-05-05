package web.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import web.model.Category;
import web.model.Todo;
import web.model.TodoList;
import web.model.User;
import web.service.TodoService;

import java.util.ArrayList;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class TodoV2Test {
    @Autowired
    private TodoService todoService;
    @Autowired
    private MockMvc mvc;
    User u;
    TodoList tl;
    Todo todo1;
    Todo todo3;
    Todo todo2;

    @BeforeEach
    void setUp() {
        u=new User();
        u.setName("Christine");
        tl=new TodoList();
        tl.setName("Jean");
        ArrayList<Todo> temp =new ArrayList<Todo>();
        todo1=new Todo("Couilles","JJJ","JJ", Category.HIGH_PRIORITY);
        todo2=new Todo("CouilleDroite","JJJ","JJ",Category.HIGH_PRIORITY);
        todo3=new Todo("CouilleGauche","JJJ","JJ",Category.HIGH_PRIORITY);
        temp.add(todo1);
        temp.add(todo2);
        temp.add(todo3);
        tl.setTodoList(temp);
        ArrayList<TodoList> temp2 =new ArrayList<TodoList>();
        temp2.add(tl);
        u.setLists(temp2);


    }

    @Test
    void getTest() throws Exception {
        mvc.perform(get("/api/insa/v1/todo/todo"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.*",hasSize(2)))
                .andExpect(jsonPath("$.todoList[0].*",hasSize(4)))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name",equalTo(null)))
                .andExpect(jsonPath("$.todoList[0].title",equalTo("la liste de lolo")));

    }

    @Test
    void coucou() throws Exception {
        mvc.perform(post("/api/insa/v1/todo/Christine/Jean").content("""
{
    "title": "title2",
    "privateDescription": "foo",
    "publicDescription": "bar,
    "categories": ["ENTERTAINMENT"]
}
	"""))
                //.andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}