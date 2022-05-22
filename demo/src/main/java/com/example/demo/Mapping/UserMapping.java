package com.example.demo.Mapping;

import com.example.demo.DAO.Phone;
import com.example.demo.DAO.User;
import com.example.demo.DTO.UserDTO;

import java.util.ArrayList;
import java.util.List;

public class UserMapping {

    public static UserDTO toDto(User user){
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getFirstname(),
                user.getLastname(),
                user.getPhonenumber(),
                user.getLocation()
        );
    }

    public static UserDTO toDto1(User user){
        List<Phone> phones = user.getPhones();

        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getFirstname(),
                user.getLastname(),
                user.getPhonenumber(),
                user.getLocation(),
                PhoneMapping.toDto1(phones)
        );
    }

    public static User toEntity(UserDTO userDTO){
        return new User(
                userDTO.getId(),
                userDTO.getUsername(),
                userDTO.getPassword(),
                userDTO.getFirstname(),
                userDTO.getLastname(),
                userDTO.getPhonenumber(),
                userDTO.getLocation(),
                new ArrayList<>()
        );
    }
}

