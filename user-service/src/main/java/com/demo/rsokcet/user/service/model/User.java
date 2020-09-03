package com.demo.rsokcet.user.service.model;

import lombok.*;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class User {

    @Id
    @GeneratedValue
    private long id;

    private String  firstName;

    private String  lastName;

    private String  mobile;

    private String  job;
}
