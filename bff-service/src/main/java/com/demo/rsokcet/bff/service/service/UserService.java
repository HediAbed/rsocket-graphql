package com.demo.rsokcet.bff.service.service;

import com.demo.rsokcet.bff.service.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {
    private final ObjectMapper objectMapper;
    private final RSocketRequester rSocketRequester;

    @GraphQLQuery(name = "users")
    public List<User> getAll(){
        return rSocketRequester.route("users/findAll")
                .retrieveFlux(String.class).toStream().map(s -> {
                    try {
                        return objectMapper.readValue(s,User.class);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    return null;
                }).collect(Collectors.toList());


    }

    @SneakyThrows
    @GraphQLQuery(name = "user")
    public User getById(@GraphQLArgument(name = "id") long id){
        return objectMapper.readValue(rSocketRequester.route("users/findById/" + id)
                .retrieveMono(String.class).block(),User.class)
         ;
    }

    @SneakyThrows
    @GraphQLMutation(name = "saveUser")
    public User save(@GraphQLArgument(name = "user") User user){
        user.setId(null);
        return objectMapper.readValue(rSocketRequester.route("users/save")
                .data(objectMapper.writeValueAsString(user))
                .retrieveMono(String.class)
                .block(),
                User.class);
    }

    @SneakyThrows
    @GraphQLMutation(name = "updateUser")
    public User update(@GraphQLArgument(name = "id") long id, @GraphQLArgument(name = "user") User user) {
        return objectMapper.readValue(rSocketRequester.route("users/update/"+ id)
                .data(objectMapper.writeValueAsString(user))
                .retrieveMono(String.class)
                .block(),User.class);
    }

    @GraphQLMutation(name = "deleteUser")
    public void delete(@GraphQLArgument(name = "id") String id){
        rSocketRequester.route("users/deleteById."+ id).send();
    }
}
