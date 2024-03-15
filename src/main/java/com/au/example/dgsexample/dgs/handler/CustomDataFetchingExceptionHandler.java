package com.au.example.dgsexample.dgs.handler;

import com.netflix.graphql.types.errors.TypedGraphQLError;
import graphql.GraphQLError;
import graphql.execution.DataFetcherExceptionHandler;
import graphql.execution.DataFetcherExceptionHandlerParameters;
import graphql.execution.DataFetcherExceptionHandlerResult;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Component
public class CustomDataFetchingExceptionHandler implements DataFetcherExceptionHandler {

    @Override
    public CompletableFuture<DataFetcherExceptionHandlerResult> handleException(DataFetcherExceptionHandlerParameters handlerParameters) {
        Map<String, Object> debugInfo = new HashMap<>();

        debugInfo.put("message", handlerParameters.getException().getMessage());
        GraphQLError graphqlError = TypedGraphQLError.newInternalErrorBuilder()
                .message(handlerParameters.getException().getClass().getName())
                .debugInfo(debugInfo)
                .path(handlerParameters.getPath()).build();
        DataFetcherExceptionHandlerResult result = DataFetcherExceptionHandlerResult.newResult()
                .error(graphqlError)
                .build();
        return CompletableFuture.completedFuture(result);


    }
}
