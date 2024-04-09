package com.github.xuchen93.cloud.filter.global;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author xuchen
 * @date 2019/12/25
 * 全局过滤器
 */
@Slf4j
//@Component
public class GlobalParamFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("[全局拦截]进入param拦截器");
        MultiValueMap<String, String> queryParams = exchange.getRequest().getQueryParams();
        log.info("请求参数:{}", JSONUtil.toJsonStr(queryParams));
        log.info("参数id的值:{}",queryParams.get("id"));
        if (!queryParams.containsKey("id")){
            exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 100;
    }
}
