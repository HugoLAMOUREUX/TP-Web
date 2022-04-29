package web.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Todo {
    private String title;
    private String publicDescription;
    private String privateDescription;
    private Category category;

    public Todo(String titlee, String publicDesc,String privateDesc,Category cat){
        title = titlee;
        publicDescription=publicDesc;
        privateDescription=privateDesc;
        category=cat;
    }


}
