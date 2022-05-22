package com.example.demo.DAO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@NamedQuery(name = "Phone.getPhoneByIsActiveAndTime",
            query = " select p from Phone p " +
                    " where p.is_active = true " +
                    " order by p.time desc ")
@Entity
@Table(name = "phone")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Phone {
    @Id
    @GeneratedValue(generator = "phone_id_seq")
    @SequenceGenerator(name = "phone_id_seq", sequenceName = "phone_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "descriptions")
    private String descriptions;

    @Column(name = "model")
    private String model;

    @Column(name = "price")
    private Integer price;

    @Column(name = "possible_torg")
    private Boolean possible_torg;

    @Column(name = "condition")
    private String condition;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "is_active", columnDefinition = "boolean default true")
    private Boolean is_active;

    @Column(name = "seen")
    private Integer seen;

    @Column(name = "time")
    private Date time;
}
