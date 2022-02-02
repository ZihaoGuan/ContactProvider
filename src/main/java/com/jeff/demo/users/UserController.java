package com.jeff.demo.users;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/getusercontacts")
	public String greeting(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "id", required = false) Long id) {
        return "test: name=" + name+ ", id=" + id;
    }
    
}
