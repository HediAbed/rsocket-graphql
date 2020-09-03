package com.demo.rsokcet.user.service.controller;

import com.demo.rsokcet.user.service.model.User;
import com.demo.rsokcet.user.service.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final ObjectMapper objectMapper;

    @MessageMapping("users/findAll")
    public Flux<User> all() {
        return userService.findAll();
    }

    @MessageMapping("users/findById/{id}")
    public Mono<User> get(@DestinationVariable("id") long id) {
        return userService.findOne(id);
    }

    @MessageMapping("users/save")
    public Mono<User> create(@Payload User user) {
        return userService.save(user);
    }

    @MessageMapping("users/update/{id}")
    public Mono<User> update(@DestinationVariable("id") long id, @Payload User user) {
        return userService.findOne(id)
                .map(u -> {
                    u.setFirstName(user.getFirstName());
                    u.setLastName(user.getFirstName());
                    u.setJob(user.getJob());
                    u.setMobile(user.getMobile());
                    return u;
                })
                .flatMap(userService::save);
    }
    @MessageMapping("users/deleteById/{id}")
    public void delete(@DestinationVariable("id") long id) {
         userService.delete(id);
    }
}
