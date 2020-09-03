package com.demo.rsokcet.bff.service.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class User {
    private Long id;

    private String  firstName;

    private String  lastName;

    private String  mobile;

    private String  job;

    public User(String firstName, String lastName, String mobile, String job) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobile = mobile;
        this.job = job;
    }
}
