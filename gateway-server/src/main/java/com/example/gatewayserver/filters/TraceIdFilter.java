package com.example.gatewayserver.filters;

import com.example.gatewayserver.utils.FilterUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@Order(1)
@Component
public class TraceIdFilter implements GlobalFilter {

    private static final Logger logger = LoggerFactory.getLogger(TraceIdFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        HttpHeaders requestHeaders = exchange.getRequest().getHeaders();
        if (FilterUtil.getCorrelationId(requestHeaders).isPresent()) {
            logger.debug("trace-id found in tracing filter: {}. ",
                    FilterUtil.getCorrelationId(requestHeaders).get());
        } else {
            String correlationID = java.util.UUID.randomUUID().toString();
            exchange = FilterUtil.setCorrelationId(exchange, correlationID);
            logger.debug("trace-id generated in tracing filter: {}.", correlationID);
        }
        return chain.filter(exchange);
    }

}
