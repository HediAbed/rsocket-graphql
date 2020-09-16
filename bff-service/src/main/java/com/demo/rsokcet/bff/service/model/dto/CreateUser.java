package com.demo.rsokcet.bff.service.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUser {
    private String  firstName;

    private String  lastName;

    private String  mobile;

    private String  job;
}
