package com.jeff.demo.users;

import com.jeff.demo.users.dto.UserDto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
	private ModelMapper modelMapper;
    @Autowired
    private UserService userService;

    @GetMapping("/getusercontacts")
    public UserDto getUserContacts(@RequestParam(value = "username", required = false) String name,
            @RequestParam(value = "id", required = false) Long id) {

        //if both parameters not exist, return user with id=-1 
        if (id == null && name == null) {
            UserDto temp = new UserDto();
            temp.setId(-1L);
            return temp;
        }

        //if both parameters are provided, return user with id=-1 
        if (id != null && name != null) {
            UserDto temp = new UserDto();
            temp.setId(-1L);
            return temp;
        }

        if (id != null) {
            return modelMapper.map(userService.getUserContactsById(id), UserDto.class);
        }

        if (name != null) {
            return modelMapper.map(userService.getUserContactsByUsername(name), UserDto.class);
        }

        UserDto temp = new UserDto();
        temp.setId(-1L);
        return temp;
    }

}
