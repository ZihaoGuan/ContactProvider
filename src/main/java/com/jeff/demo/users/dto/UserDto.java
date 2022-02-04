package com.jeff.demo.users.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @ToString
public class UserDto {
    private Long id;
    private String email;
    private String phone;

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