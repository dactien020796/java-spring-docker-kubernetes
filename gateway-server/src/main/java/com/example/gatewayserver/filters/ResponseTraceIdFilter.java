package com.example.gatewayserver.filters;

import com.example.gatewayserver.utils.FilterUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Configuration
public class ResponseTraceIdFilter {

    private static final Logger logger = LoggerFactory.getLogger(ResponseTraceIdFilter.class);

    @Bean
    public GlobalFilter postGlobalFilter() {
        return (exchange, chain) -> chain.filter(exchange)
            .then(Mono.fromRunnable(() -> {
                HttpHeaders requestHeaders = exchange.getRequest().getHeaders();
                String correlationId = FilterUtil.getCorrelationId(requestHeaders)
                        .orElse(UUID.randomUUID().toString());
                logger.debug("Updated the correlation id to the outbound headers. {}", correlationId);
                exchange.getResponse().getHeaders().add(FilterUtil.CORRELATION_ID, correlationId);
            }
        ));
    }
}
