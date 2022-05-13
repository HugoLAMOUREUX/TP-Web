package web.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
@JsonSubTypes({
        @JsonSubTypes.Type(value= SpecificTodo.class, name = "SpecificTodo"), //par défaut le name est celui de la classe

})
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME,include=JsonTypeInfo.As.PROPERTY,property = "type",defaultImpl = Todo.class) //type -> utilisé pour savoir le constructeur
public class Todo {
    private String title;
    private String publicDescription;
    private String privateDescription;
    private Category category;

    /*public Todo(String titlee, String publicDesc,String privateDesc,Category cat){
        title = titlee;
        publicDescription=publicDesc;
        privateDescription=privateDesc;
        category=cat;
    }*/
    public Todo(){}


}
