package com.example.demo.Controller;

import com.example.demo.DAO.User;
import com.example.demo.DTO.ResponseDTO;
import com.example.demo.DTO.UserDTO;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Servise.UserServise;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserServise userServise;

    @GetMapping("/get-user")
    public ResponseDTO<UserDTO> getUser(@RequestParam Integer id){
        return userServise.getUser(id);
    }

    @PostMapping("/add-user")
    public ResponseDTO<UserDTO> addUser(@RequestBody UserDTO userDTO){
        return userServise.addUser(userDTO);
    }

    @DeleteMapping("/delete-user")
    public ResponseDTO<UserDTO> deleteUser(@RequestBody UserDTO userDTO){
        return userServise.deleteUser(userDTO);
    }

    @PutMapping("/update-user")
    public ResponseDTO<UserDTO> updateUser(@RequestBody UserDTO userDTO){
        return userServise.updateUser(userDTO);
    }
}
