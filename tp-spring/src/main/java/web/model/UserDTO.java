package web.model;

import fr.insarennes.demo.model.User;
import lombok.Getter;
import lombok.Setter;

// Lombok annotations: generates getters and setters
// Getters and setters are required for (un-)marshalling the object
@Getter
@Setter
// We could have define a record instead of a class
public class UserDTO {
    private String name;
    private String address;
    private String id;

    public UserDTO(final User user) {
        super();
        this.name = user.getName();
        this.address = user.getAddress();
        this.id = user.getId();
    }

    public void patch(final User user) {
        if(address != null) {
            user.setAddress(address);
        }
        if(name != null) {
            user.setName(name);
        }
    }
}
