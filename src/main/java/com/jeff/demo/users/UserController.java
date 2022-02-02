package com.jeff.demo.users;

import com.jeff.demo.users.dto.UserDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserService userService;

    @GetMapping("/getusercontacts")
    public UserDto getUserContacts(@RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "id", required = false) String id) {

        UserDto result = userNotFound();
        
        if (id != null && username != null) {
            try {
                UserDto result1 = modelMapper.map(userService.getUserContactsById(Long.parseLong(id)), UserDto.class);
                UserDto result2 = modelMapper.map(userService.getUserContactsByUsername(username), UserDto.class);

                result = result1.equals(result2) ? result1 : userNotFound();
            } catch (java.lang.NumberFormatException e) {
                logger.error("Request with invalid ID");
            }
        } else if (id != null) {
            try {
                result = modelMapper.map(userService.getUserContactsById(Long.parseLong(id)), UserDto.class);
            } catch (java.lang.NumberFormatException e) {
                logger.error("Request with invalid ID");
            }
        } else if (username != null)
            result = modelMapper.map(userService.getUserContactsByUsername(username), UserDto.class);

        return result;
    }

    private UserDto userNotFound() {
        UserDto temp = new UserDto();
        temp.setId(-1L);
        return temp;
    }

}