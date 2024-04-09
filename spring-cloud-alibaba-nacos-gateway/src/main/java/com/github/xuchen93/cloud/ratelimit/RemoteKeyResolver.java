package com.github.xuchen93.cloud.ratelimit;

import cn.hutool.system.HostInfo;
import cn.hutool.system.SystemUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 根据id限流
 */
@Slf4j
//@Component("remoteKeyResolver")
public class RemoteKeyResolver implements KeyResolver {

    @Override
    public Mono<String> resolve(ServerWebExchange exchange) {
        HostInfo hostInfo = SystemUtil.getHostInfo();
        return Mono.just(hostInfo.getAddress());
    }
}
