package com.demo.rsokcet.bff.service.controller;




import com.demo.rsokcet.bff.service.service.UserService;
import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import io.leangen.graphql.GraphQLSchemaGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class GraphQLController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GraphQLController.class);
    private final GraphQL graphQL;

    public GraphQLController(GraphQL graphQL) {
        this.graphQL = graphQL;
    }

    @Autowired
    public GraphQLController(UserService userService) {

        //Schema generated from query classes
        GraphQLSchema schema = new GraphQLSchemaGenerator()
                .withBasePackages("com.demo.rsocket.bff.service")
                .withOperationsFromSingletons(userService)
                .generate();
        graphQL = GraphQL.newGraphQL(schema).build();
        LOGGER.info("Generated GraphQL schema using SPQR");
    }

    @PostMapping(value = "/graphql")
    @ResponseBody
    public Map<String, Object> indexFromAnnotated(@RequestBody Map<String, String> request, HttpServletRequest raw) {
        ExecutionResult executionResult = graphQL.execute(ExecutionInput.newExecutionInput()
                .query(request.get("query"))
                .operationName(request.get("operationName"))
                .context(raw)
                .build());
        return executionResult.toSpecification();
    }
}
