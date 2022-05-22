package com.example.demo.Repository;

import com.example.demo.DAO.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Integer> {
    List<Phone> getPhoneByIsActiveAndTime();

    List<Phone> getByModel(String model);
}
