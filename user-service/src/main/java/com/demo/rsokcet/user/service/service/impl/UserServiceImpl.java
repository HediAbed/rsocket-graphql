package com.demo.rsokcet.user.service.service.impl;

import com.demo.rsokcet.user.service.model.User;
import com.demo.rsokcet.user.service.model.dto.CreateUser;
import com.demo.rsokcet.user.service.repository.UserRepository;
import com.demo.rsokcet.user.service.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.persistence.EntityNotFoundException;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository repository;

    @Override
    public Mono<User> create(CreateUser user) {
        return Mono.just(repository.save(new User(user)));
    }

    @Override
    public Mono<User> update(User user) {
        repository.findById(user.getId()).orElseThrow(EntityNotFoundException::new);
        return Mono.just( repository.save(user));
    }

    @Override
    public Flux<User> findAll() {
        return Flux.fromIterable(repository.findAll());
    }

    @Override
    public Mono<User> findOne(long id) {
        return Mono.just(repository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    @Override
    public void delete(long id) {
         repository.deleteById(id);
    }
}
