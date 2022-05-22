package com.example.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private Integer id;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String phonenumber;
    private String location;
    private List<PhoneDTO> phones;

    public UserDTO(Integer id, String username, String password, String firstname, String lastname, String phonenumber, String location) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phonenumber = phonenumber;
        this.location = location;
    }

    public UserDTO(Integer id){
        this.id = id;
    }
}
