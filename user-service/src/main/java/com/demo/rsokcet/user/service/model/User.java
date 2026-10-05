package com.demo.rsokcet.user.service.model;

import com.demo.rsokcet.user.service.model.dto.CreateUser;
import lombok.*;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private long id;

    private String  firstName;

    private String  lastName;

    private String  mobile;

    private String  job;

    public User(CreateUser user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.mobile = user.getMobile();
        this.job = user.getJob();
    }
}
