package com.demo.rsokcet.user.service.controller;

import com.demo.rsokcet.user.service.model.User;
import com.demo.rsokcet.user.service.model.dto.CreateUser;
import com.demo.rsokcet.user.service.service.UserService;
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

    @MessageMapping("users/find/all")
    public Flux<User> all() {
        return userService.findAll();
    }

    @MessageMapping("users/find/{id}")
    public Mono<User> get(@DestinationVariable("id") long id) {
        return userService.findOne(id);
    }

    @MessageMapping("users/create")
    public Mono<User> create(@Payload CreateUser user) {
        return userService.create(user);
    }

    @MessageMapping("users/update")
    public Mono<User> update(@Payload User user) {
        return userService.update(user);
    }

    @MessageMapping("users/delete/{id}")
    public void delete(@DestinationVariable("id") long id) {
        userService.delete(id);
    }
}
