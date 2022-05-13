package web.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class SpecificTodo extends Todo{
    private String mySpecificAttrb;


}
