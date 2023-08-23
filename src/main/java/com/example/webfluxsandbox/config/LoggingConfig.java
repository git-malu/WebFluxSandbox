package com.example.webfluxsandbox.config;

import org.slf4j.MDC;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

@Configuration
public class LoggingConfig {

    @Bean
    public WebFilter logRequest() {
        return (ServerWebExchange exchange, WebFilterChain chain) -> {
            // Get the request id from the X-Request-ID header
            String requestId = exchange.getRequest().getHeaders().getFirst("X-Request-ID");

            // Put the request id into the MDC
            MDC.put("requestId", requestId);

            // Make sure to remove the MDC variable when the request is finished
            return chain.filter(exchange).doFinally(sig -> MDC.remove("requestId"));
        };
    }
}
