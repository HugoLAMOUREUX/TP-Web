package web.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TodoListTest {

    User u;
    TodoList tl;
    Todo todo1;
    Todo todo3;
    Todo todo2;

    @BeforeEach
    public void setup(){
        u=new User();
        u.setName("Christine");
        tl=new TodoList();
        tl.setName("Jean");
        ArrayList<Todo> temp =new ArrayList<Todo>();
        todo1=new Todo("Couilles","JJJ","JJ",Category.HIGH_PRIORITY);
        todo2=new Todo("CouilleDroite","JJJ","JJ",Category.HIGH_PRIORITY);
        todo3=new Todo("CouilleGauche","JJJ","JJ",Category.HIGH_PRIORITY);
        temp.add(todo1);
        temp.add(todo2);
        temp.add(todo3);
        tl.setTodoList(temp);
    }


    @Test
    void findTodo() {
        assertEquals(todo1, tl.findTodo("Couilles"));
    }
    @Test
    void findTodo2() {
        assertNull(tl.findTodo("Couillessssd"));
    }

}