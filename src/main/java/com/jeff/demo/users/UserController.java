package com.jeff.demo.users;

import com.jeff.demo.users.dto.UserDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getusercontacts")
    public UserDto getUserContacts(@RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "id", required = false) String id) {

        return userService.getUserContacts(id, username);
    }

}