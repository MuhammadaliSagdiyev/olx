package com.example.demo.Servise;

import com.example.demo.DAO.User;
import com.example.demo.DTO.ResponseDTO;
import com.example.demo.DTO.UserDTO;
import com.example.demo.Mapping.PhoneMapping;
import com.example.demo.Mapping.UserMapping;
import com.example.demo.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServise {

    private final UserRepository userRepository;
    public ResponseDTO<UserDTO> getUser(Integer id) {
        User user = userRepository.findById(id).get();
        return new ResponseDTO<>(true, 0, "OK", UserMapping.toDto1(user));
    }


    public ResponseDTO<UserDTO> addUser(UserDTO userDTO) {

        try {
            String username = userDTO.getUsername();
            User user = userRepository.getByUsername(username);
            if (user != null){
                return new ResponseDTO<>(false, -1, "Bu username mavjud", userDTO);
            }
        } catch (Exception e) {

        }


        if (userDTO == null) {
            return new ResponseDTO<>(false, -1, "UserDTO null bo'lishi mumkin emas", userDTO);
        } else if (userDTO.getUsername() == null || userDTO.getUsername().isEmpty()){
            return new ResponseDTO<>(false, -1, "Username null bo'lishi mumkin emas", userDTO);
        } else if (userDTO.getPassword() == null) {
            return new ResponseDTO<>(false, -1, "Password null bo'lishi mumkin emas", userDTO);
        } else if (userDTO.getFirstname() == null) {
            return new ResponseDTO<>(false, -1, "Ism null bo'lishi mumkin emas", userDTO);
        } else if (userDTO.getPhonenumber() == null) {
            return new ResponseDTO<>(false, -1, "Telefon raqam null bo'lishi mumkin emas", userDTO);
        } else if (userDTO.getLocation() == null) {
            return new ResponseDTO<>(false, -1, "Manzil null bo'lishi mumkin emas", userDTO);
        }
        userRepository.save(UserMapping.toEntity(userDTO));return new ResponseDTO<>(true, 0, "User qo'shildi", userDTO);

    }

    public ResponseDTO<UserDTO> deleteUser(UserDTO userDTO) {
        if (userDTO == null || userDTO.getUsername() == null || userDTO.getPassword() == null || userDTO.getId() == null){
            return new ResponseDTO<>(false, -1, "Username, Password, Id kiritish majburiy", userDTO);
        }
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        Integer id = userDTO.getId();

        try {
            User user = userRepository.findById(id).get();
            if (user.getUsername().equals(username) && user.getPassword().equals(password)){
                userRepository.delete(user);
                return new ResponseDTO<>(true, 0, "Akkaunt o'chdi", null);
            }
            return new ResponseDTO<>(false, -1, "Username yoki password xato", userDTO);
        } catch (Exception e) {
            return new ResponseDTO<>(false, -1, "User topilmadi", userDTO);
        }

    }

    public ResponseDTO<UserDTO> updateUser(UserDTO userDTO) {
        if (userDTO.getId() == null){
            return new ResponseDTO<>(false, -1, "ID null bo'olishi mumkin emas", userDTO);
        }
        try {
            User user = userRepository.findById(userDTO.getId()).get();

            if (userDTO.getFirstname() == null){
                userDTO.setFirstname(user.getFirstname());
            }
            if (userDTO.getLastname() == null){
                userDTO.setLastname(user.getLastname());
            }
            if (userDTO.getPhonenumber() == null){
                userDTO.setPhonenumber(user.getPhonenumber());
            }
            if (userDTO.getLocation() == null){
                userDTO.setLocation(user.getLocation());
            }
            if (userDTO.getPhones() == null || userDTO.getPhones().isEmpty()){
                userDTO.setPhones(PhoneMapping.toDto1(user.getPhones()));
            }
            userRepository.save(UserMapping.toEntity(userDTO));
            return new ResponseDTO<>(true, 0, "Akkaunt malumotlari o'zgardi", userDTO);

        } catch (Exception e) {
            return new ResponseDTO<>(false, -1, userDTO.getId() + " ID lik User topilmadi", userDTO);
        }
    }
}