package com.example.demo.Controller;

import com.example.demo.DTO.PhoneDTO;
import com.example.demo.DTO.ResponseDTO;
import com.example.demo.Servise.PhoneServise;
import lombok.RequiredArgsConstructor;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PhoneController {

    private final PhoneServise phoneServise;

    @GetMapping("/get-all-phones")
    public ResponseDTO<List<PhoneDTO>> getAll(){
        return phoneServise.getAll();
    }

    @PostMapping("/add_phone")
    public ResponseDTO<PhoneDTO> addPhone(@RequestBody PhoneDTO phoneDTO){
        return phoneServise.addPhone(phoneDTO);
    }

    @DeleteMapping("/delete-phone")
    public ResponseDTO<PhoneDTO> deletePhone(@RequestBody PhoneDTO phoneDTO){
        return phoneServise.deletePhone(phoneDTO);
    }

    @PutMapping("/update-phone")
    public ResponseDTO<PhoneDTO> updatePhone(@RequestBody PhoneDTO phoneDTO){
        return phoneServise.updatePhone(phoneDTO);
    }

    @GetMapping("/get-phone-by-id")
    public ResponseDTO<PhoneDTO> getPhoneById(@RequestParam Integer id){
        return phoneServise.getPhoneById(id);
    }


    @GetMapping("/get-by-model")
    public ResponseDTO<List<PhoneDTO>> getByParam(@RequestParam String model){
        return phoneServise.getByModel(model);
    }

}