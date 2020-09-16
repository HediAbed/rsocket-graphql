package com.demo.rsokcet.user.service.config;

import com.demo.rsokcet.user.service.model.User;
import com.demo.rsokcet.user.service.model.dto.CreateUser;
import com.demo.rsokcet.user.service.service.UserService;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class InitData implements ApplicationRunner {
    @Autowired
    private UserService userService;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        Faker faker = new Faker();

        for (int i=0; i<10;i++){
            CreateUser user = new CreateUser();
            user.setMobile(faker.phoneNumber().cellPhone());
            user.setFirstName(faker.name().firstName());
            user.setLastName(faker.name().lastName());
            user.setJob(faker.job().position());
            userService.create(user);
        }


    }
}
