package com.example.webfluxsandbox.config;

import reactor.core.publisher.Mono;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

@Component
class LoggingWebFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        // Get the request id from the X-Request-ID header
        String requestId = exchange.getRequest().getHeaders().getFirst("X-Request-Id");

        // Put the request id into the MDC
        MDC.put("X-Request-Id", requestId);

        // Make sure to remove the MDC variable when the request is finished
        return chain.filter(exchange).doFinally(sig -> MDC.remove("X-Request-Id"));
    }
}
