package com.example.gatewayserver.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.web.server.ServerWebExchange;

import java.util.List;
import java.util.Optional;

public class FilterUtil {

    public static final String CORRELATION_ID = "trace-id";

    private FilterUtil() {}

    public static Optional<String> getCorrelationId(HttpHeaders requestHeaders) {
        if (requestHeaders.get(CORRELATION_ID) != null) {
            List<String> requestHeaderList = requestHeaders.get(CORRELATION_ID);
            return Optional.of(requestHeaderList.stream().findFirst().get());
        } else {
            return Optional.empty();
        }
    }

    public static ServerWebExchange setRequestHeader(ServerWebExchange exchange, String name, String value) {
        return exchange.mutate().request(exchange.getRequest().mutate().header(name, value).build()).build();
    }

    public static ServerWebExchange setCorrelationId(ServerWebExchange exchange, String correlationId) {
        return setRequestHeader(exchange, CORRELATION_ID, correlationId);
    }
}
