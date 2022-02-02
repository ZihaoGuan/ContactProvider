package com.jeff.demo.users;

import java.util.Arrays;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jeff.demo.users.model.User;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    private User[] users;

    // read and store data from thrid party API.
    public UserService() {

        final String uri = "https://jsonplaceholder.typicode.com/users";

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);

        ObjectMapper mapper = new ObjectMapper();
        try {
            users = mapper.readValue(result, User[].class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public User getUserContactsById(Long id) {
        User[] filteredUsers = Arrays.stream(users).filter(u -> u.getId() == id).toArray(User[]::new);

        if (filteredUsers.length == 0) {
            User temp = new User();
            temp.setId(-1L);
            return temp;
        }

        return filteredUsers[0];
    }

    public User getUserContactsByUsername(String username) {
        User[] filteredUsers = Arrays.stream(users).filter(u -> u.getUsername().equals(username)).toArray(User[]::new);

        if (filteredUsers.length == 0) {
            User temp = new User();
            temp.setId(-1L);
            return temp;
        }

        return filteredUsers[0];
    }
}