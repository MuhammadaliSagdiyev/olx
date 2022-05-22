package com.example.demo.Mapping;

import com.example.demo.DAO.Phone;
import com.example.demo.DAO.User;
import com.example.demo.DTO.PhoneDTO;

import java.util.ArrayList;
import java.util.List;

public class PhoneMapping {

    public static PhoneDTO toDto(Phone phone){
        return new PhoneDTO(
                phone.getId(),
                phone.getName(),
                phone.getDescriptions(),
                phone.getModel(),
                phone.getPrice(),
                phone.getPossible_torg(),
                phone.getCondition(),
                UserMapping.toDto(phone.getUser()),
                phone.getIs_active(),
                phone.getSeen(),
                phone.getTime()
        );
    }

    public static List<PhoneDTO> toDto1(List<Phone> phone1){
        List<PhoneDTO> phoneDTOS = new ArrayList<>();
        for (Phone phone : phone1){
            phoneDTOS.add(new PhoneDTO(
                    phone.getId(),
                    phone.getName(),
                    phone.getDescriptions(),
                    phone.getModel(),
                    phone.getPrice(),
                    phone.getPossible_torg(),
                    phone.getCondition(),
                    null,
                    phone.getIs_active(),
                    phone.getSeen(),
                    phone.getTime()
            ));
        }
        return phoneDTOS;
    }

    public static Phone toEntity(PhoneDTO phoneDTO){
        if (phoneDTO == null){
            return null;
        }


        return new Phone(
                phoneDTO.getId(),
                phoneDTO.getName(),
                phoneDTO.getDescriptions(),
                phoneDTO.getModel(),
                phoneDTO.getPrice(),
                phoneDTO.getPossible_torg(),
                phoneDTO.getCondition(),
                new User(phoneDTO.getUser().getId()),
                phoneDTO.getIs_active(),
                phoneDTO.getSeen(),
                phoneDTO.getTime()
        );
    }

}
