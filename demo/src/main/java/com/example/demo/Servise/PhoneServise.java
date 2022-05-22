package com.example.demo.Servise;

import com.example.demo.DAO.Phone;
import com.example.demo.DAO.User;
import com.example.demo.DTO.PhoneDTO;
import com.example.demo.DTO.ResponseDTO;
import com.example.demo.Mapping.PhoneMapping;
import com.example.demo.Mapping.UserMapping;
import com.example.demo.Repository.PhoneRepository;
import com.example.demo.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PhoneServise {

    private final PhoneRepository phoneRepository;
    private final UserRepository userRepository;

    public ResponseDTO<List<PhoneDTO>> getAll(){
        List<Phone> phone = phoneRepository.getPhoneByIsActiveAndTime();
        if (!phone.isEmpty()){
            return new ResponseDTO<>(true, 0, "OK", phone.stream().map(PhoneMapping::toDto).collect(Collectors.toList()));        }
        return new ResponseDTO<>(false, -1, "Error", null);
    }

    public ResponseDTO<PhoneDTO> addPhone(PhoneDTO phoneDTO) {
        if (phoneDTO.getName() == null || phoneDTO.getName().isEmpty()){
            return new ResponseDTO<>(false, -1, "Telefon nomi null bo'lishi mumkin emas", null);
        } else if (phoneDTO.getDescriptions() == null) {
            return new ResponseDTO<>(false, -1, "Opisaniya null bo'lishi mumkin emas", null);
        } else if (phoneDTO.getModel() == null) {
            return new ResponseDTO<>(false, -1, "Model null bo'lishi mumkin emas", null);
        } else if (phoneDTO.getPrice() == null) {
            return new ResponseDTO<>(false, -1, "Telefon narxi null bo'lishi mumkin emas", null);
        } else if (phoneDTO.getCondition() == null) {
            return new ResponseDTO<>(false, -1, "Telefon xolati null bo'lishi mumkin emas", null);
        } else if (phoneDTO.getUser().getId() == null || phoneDTO.getUser() == null) {
            return new ResponseDTO<>(false, -1, "User id null bo'lishi mumkin emas", null);
        } else {

            String username = phoneDTO.getUser().getUsername();
            String password = phoneDTO.getUser().getPassword();
            try {
                User user = userRepository.getByUsername(phoneDTO.getUser().getUsername());
                if (phoneDTO.getUser().getId() != user.getId()){
                    return new ResponseDTO<>(false, -1, "Boshqa userga reklama qo'yomisiz", null);
                }

                if (user.getUsername().equals(username) && user.getPassword().equals(password)){
                    Phone phone = PhoneMapping.toEntity(phoneDTO);
                    try {
                        Phone base = phoneRepository.findById(phone.getId()).get();
                        return new ResponseDTO<>(false, -1, "Bu ID lik telefon mavjud, boshqa ID kiriting", null);
                    } catch (Exception e){

                    }

                    if (phone.getPossible_torg() == null){
                        phone.setPossible_torg(false);
                    }
                    if (phone.getIs_active() == null){
                        phone.setIs_active(true);
                    }
                    if (phone.getSeen() == null){
                        phone.setSeen(0);
                    }
                    if(phone.getTime() == null){
                        phone.setTime(Date.valueOf(LocalDate.now()));
                    }
                    phoneRepository.save(phone);
                    return new ResponseDTO<>(true, 0, "Telefon bazaga qo'shildi", PhoneMapping.toDto(phone));
                } else {
                    return new ResponseDTO<>(false, -1, "Password xato", null);
                }
            } catch (Exception e) {
                return new ResponseDTO<>(false, -1, "User tomilmadi", null);
            }
        }
    }

    public ResponseDTO<PhoneDTO> deletePhone(PhoneDTO phoneDTO) {
        try {
            Phone phone = phoneRepository.findById(phoneDTO.getId()).get();

            String username = phoneDTO.getUser().getUsername();
            String password = phoneDTO.getUser().getPassword();

            try {
                User user = userRepository.getByUsername(phoneDTO.getUser().getUsername());
                if (phoneDTO.getUser().getId() != user.getId()){
                    return new ResponseDTO<>(false, -1, "Faqat o'zingizning reklamangizni o'chira olasiz", null);
                }
                if (user.getUsername().equals(username) && user.getPassword().equals(password)){
                    phoneRepository.deleteById(phoneDTO.getId());
                    return new ResponseDTO<>(true, 0, "OK", PhoneMapping.toDto(phone));
                } else {
                    return new ResponseDTO<>(false, -1, "Password xato", null);
                }
            } catch (Exception e) {
                return new ResponseDTO<>(false,
                        1, "User tomilmadi", null);
            }
        } catch (Exception e) {
            return new ResponseDTO<>(false, -1, "Bu ID lik telefon yo'q", null);
        }
    }


    public ResponseDTO<PhoneDTO> updatePhone(PhoneDTO phoneDTO) {
        if (phoneDTO == null){
            return new ResponseDTO<>(false, -1, "Phone null bo'lishi mumkin emas", null);
        }
        if (phoneDTO.getId() == null){
            return new ResponseDTO<>(false, -1, "Phone ID null bo'lishi mumkin emas", null);
        }
        try {
            Phone phone = phoneRepository.findById(phoneDTO.getId()).get();

            if (phoneDTO.getName() == null) {
                phoneDTO.setName(phone.getName());
            }
            if (phoneDTO.getDescriptions() == null) {
                phoneDTO.setDescriptions(phone.getDescriptions());
            }
            if (phoneDTO.getModel() == null) {
                phoneDTO.setModel(phone.getModel());
            }
            if (phoneDTO.getPrice() == null) {
                phoneDTO.setPrice(phone.getPrice());
            }
            if (phoneDTO.getPossible_torg() == null) {
                phoneDTO.setPossible_torg(phone.getPossible_torg());
            }
            if (phoneDTO.getCondition() == null) {
                phoneDTO.setCondition(phone.getCondition());
            }
            if (phoneDTO.getUser() == null) {
                phoneDTO.setUser(UserMapping.toDto(phone.getUser()));
            }
            if (phoneDTO.getIs_active() == null) {
                phoneDTO.setIs_active(phone.getIs_active());
            }
            if (phoneDTO.getSeen() == null) {
                phoneDTO.setSeen(phone.getSeen());
            }
            if (phoneDTO.getTime() == null) {
                phoneDTO.setTime(phone.getTime());
            }

            Phone phone1 = PhoneMapping.toEntity(phoneDTO);
            phoneRepository.save(phone1);
            return new ResponseDTO<>(true, 0, "Malumot o;zgardi", PhoneMapping.toDto(phone1));
        } catch (Exception e) {
            return new ResponseDTO<>(false, -1, "Yo'q reklamani o'zgartirib bo'midi", null);
        }
    }

    public ResponseDTO<PhoneDTO> getPhoneById(Integer id) {
        if (id < 1){
            return new ResponseDTO<>(false, -1, "ID 1 dan kichik bo'lishi mumkin emas", null);
        }
        try {
            Phone phone = phoneRepository.findById(id).get();
            if (phone.getSeen() == null){
                phone.setSeen(0);
            }
            Integer seen = phone.getSeen() + 1;
            phone.setSeen(seen);
            phoneRepository.save(phone);
            return new ResponseDTO<>(true, 0, "OK", PhoneMapping.toDto(phone));
        } catch (Exception e) {
            return new ResponseDTO<>(false, -1, id + " ID li telefon topilmadi", null);
        }
    }

    public ResponseDTO<List<PhoneDTO>> getByModel(String model) {
        List<Phone> phones = phoneRepository.getByModel(model);
        return new ResponseDTO<>(true, 0, "OK", PhoneMapping.toDto1(phones));
    }

}
