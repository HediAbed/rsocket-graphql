package com.demo.rsokcet.bff.service.service;

import com.demo.rsokcet.bff.service.model.User;
import com.demo.rsokcet.bff.service.model.dto.CreateUser;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class UserService {
    private final RSocketRequester rSocketRequester;

    @GraphQLQuery(name = "users")
    public Flux<User> getAll(){
        return rSocketRequester.route("users/find/all").retrieveFlux(User.class);
    }

    @SneakyThrows
    @GraphQLQuery(name = "user")
    public Mono<User> getById(@GraphQLArgument(name = "id") long id){
        return rSocketRequester.route("users/find/" + id).retrieveMono(User.class);
    }

    @SneakyThrows
    @GraphQLMutation(name = "saveUser")
    public Mono<User> save(@GraphQLArgument(name = "user") CreateUser user){
        return rSocketRequester.route("users/create").data(user).retrieveMono(User.class);
    }

    @SneakyThrows
    @GraphQLMutation(name = "updateUser")
    public Mono<User> update(@GraphQLArgument(name = "user") User user) {
        return rSocketRequester.route("users/update").data(user).retrieveMono(User.class);
    }

    @GraphQLMutation(name = "deleteUser")
    public void delete(@GraphQLArgument(name = "id") long id){
        rSocketRequester.route("users/deleteById."+ id).send();
    }
}
