package com.demo.rsokcet.user.service.model;

import com.demo.rsokcet.user.service.model.dto.CreateUser;
import lombok.*;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
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
