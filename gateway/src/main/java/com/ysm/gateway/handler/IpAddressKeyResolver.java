package com.ysm.gateway.handler;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class IpAddressKeyResolver implements KeyResolver {
    @Override
    public Mono<String> resolve(ServerWebExchange exchange) {
        //默认为令牌桶限流
        return Mono.just(exchange.getRequest().getRemoteAddress().
                         getHostString());
    }
}