package com.example.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PhoneDTO {
    private Integer id;
    private String name;
    private String descriptions;
    private String model;
    private Integer price;
    private Boolean possible_torg;
    private String condition;
    private UserDTO user;
    private Boolean is_active;
    private Integer seen;
    private Date time;
}
