package com.jeff.demo.users;

import com.jeff.demo.users.model.User;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    public User getUserContactsById(Long id) {
        User temp = new User();
        temp.setId(id);
        return temp;
    }
    public User getUserContactsByUsername(String username) {
        User temp = new User();
        temp.setUsername(username);
        return temp;
    }
}