package com.jeff.demo.users;

import java.util.Arrays;

import com.jeff.demo.users.dto.UserDto;
import com.jeff.demo.users.model.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);
    
    @Autowired
    private RestTemplate restTemplate;

    public UserDto getUserContacts(String idString, String username){

        UserDto result = new UserDto(userNotFound());

        if (idString != null && username != null) {
            try {
                UserDto result1 = new UserDto(getUserContactsById(Long.parseLong(idString)));
                UserDto result2 = new UserDto(getUserContactsByUsername(username));

                result = result1.equals(result2) ? result1 : result;
            } catch (java.lang.NumberFormatException e) {
                logger.error("Request with invalid ID");
            }
        } else if (idString != null) {
            try {
                result = new UserDto(getUserContactsById(Long.parseLong(idString)));
            } catch (java.lang.NumberFormatException e) {
                logger.error("Request with invalid ID");
            }
        } else if (username != null)
            result = new UserDto(getUserContactsByUsername(username));

        return result;
    }

    private User getUserContactsById(Long id) {
        User[] filteredUsers = Arrays.stream(fetchUserContacts()).filter(u -> u.getId() == id).toArray(User[]::new);
        return filteredUsers.length == 0 ? userNotFound() : filteredUsers[0];
    }

    private User getUserContactsByUsername(String username) {
        User[] filteredUsers = Arrays.stream(fetchUserContacts()).filter(u -> u.getUsername().equals(username)).toArray(User[]::new);
        return filteredUsers.length == 0 ? userNotFound() : filteredUsers[0];
    }

    private User[] fetchUserContacts(){
        logger.info("Fetching data from third party API.");
        String uri = "https://jsonplaceholder.typicode.com/users";
        ResponseEntity<User[]>  users = restTemplate.getForEntity(uri, User[].class);
        return users.getBody();
    }

    private User userNotFound() {
        User temp = new User();
        temp.setId(-1L);
        return temp;
    }
}