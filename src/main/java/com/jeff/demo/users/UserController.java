package com.jeff.demo.users;

import com.jeff.demo.users.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getusercontacts")
    public User greeting(@RequestParam(value = "username", required = false) String name,
            @RequestParam(value = "id", required = false) Long id) {

        //if both parameters not exist, return user with id=-1 
        if (id == null && name == null) {
            User temp = new User();
            temp.setId(-1L);
            return temp;
        }

        //if both parameters are provided, return user with id=-1 
        if (id != null && name != null) {
            User temp = new User();
            temp.setId(-1L);
            return temp;
        }

        if (id != null) {
            return userService.getUserContactsById(id);
        }

        if (name != null) {
            return userService.getUserContactsByUsername(name);
        }

        User temp = new User();
        temp.setId(-1L);
        return temp;
    }

}
