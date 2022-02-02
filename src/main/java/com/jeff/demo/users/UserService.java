package com.jeff.demo.users;

import java.util.Arrays;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jeff.demo.users.model.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);

    public User getUserContactsById(Long id) {
        User[] filteredUsers = Arrays.stream(fetchUserContacts()).filter(u -> u.getId() == id).toArray(User[]::new);
        return filteredUsers.length == 0 ? userNotFound() : filteredUsers[0];
    }

    public User getUserContactsByUsername(String username) {
        User[] filteredUsers = Arrays.stream(fetchUserContacts()).filter(u -> u.getUsername().equals(username)).toArray(User[]::new);
        return filteredUsers.length == 0 ? userNotFound() : filteredUsers[0];
    }

    private User[] fetchUserContacts(){
        User [] users = {};

        logger.info("Fetching data from third party API.");
        String uri = "https://jsonplaceholder.typicode.com/users";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        ObjectMapper mapper = new ObjectMapper();
        try {
            users = mapper.readValue(result, User[].class);
            logger.info("Data fetched successfully.");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return users;
    }

    private User userNotFound() {
        User temp = new User();
        temp.setId(-1L);
        return temp;
    }
}