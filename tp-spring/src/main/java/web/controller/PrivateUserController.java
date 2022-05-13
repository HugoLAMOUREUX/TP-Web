package web.controller;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.ServletException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;
import web.model.UserDTO;
import web.service.UserService;

@RestController
@RequestMapping("api/private/")
@AllArgsConstructor
public class PrivateUserController {

    private final UserService userService;

    @PostMapping(value = "new", consumes = MediaType.APPLICATION_JSON_VALUE)
        public void newAccount(@RequestBody final UserDTO user) {
            try {
                userService.newAccount(user.login(), user.pwd());
            }catch(final IllegalArgumentException ex) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not possible");
            }
        }

        @PostMapping(value = "login", consumes = MediaType.APPLICATION_JSON_VALUE)
        public void login(@RequestBody final UserDTO user) {
            try {
                final boolean logged = userService.login(user.login(), user.pwd());

                if(!logged) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Already logged in. Log out first");
                }
            }catch(final ServletException ex) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not possible to log in");
            }
        }
    }


    record UserDTO(String login, String pwd) {

    }

}
