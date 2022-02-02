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
    public UserDto getUserContacts(@RequestParam(value = "username", required = false) String name,
            @RequestParam(value = "id", required = false) String id) {

        // if both parameters not exist, return user with id=-1
        if (id == null && name == null)
            return userNotFound();

        // if both parameters are provided, return user with id=-1
        if (id != null && name != null)
            return userNotFound();

        if (id != null){
            try {
                return modelMapper.map(userService.getUserContactsById(Long.parseLong(id)), UserDto.class);
            } catch (java.lang.NumberFormatException e){
                logger.error("Request with invalid ID");
                return userNotFound();
            }
        }  

        if (name != null)
            return modelMapper.map(userService.getUserContactsByUsername(name), UserDto.class);

        return userNotFound();
    }

    private UserDto userNotFound() {
        UserDto temp = new UserDto();
        temp.setId(-1L);
        return temp;
    }

}
