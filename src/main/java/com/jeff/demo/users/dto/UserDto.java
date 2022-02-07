package com.jeff.demo.users.dto;

import com.jeff.demo.users.model.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class UserDto {
    private Long id;
    private String email;
    private String phone;

    public UserDto(){
    }

    public UserDto(User user){
        this.id = user.getId();
        this.email = user.getEmail();
        this.phone = user.getPhone();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    @Override
    public boolean equals(Object o) {
        return this.id == ((UserDto) o).getId();
    }

}