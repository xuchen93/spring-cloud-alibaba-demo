package com.github.xuchen93.cloud.filter.global;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.github.xuchen93.cloud.model.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author xuchen
 * @date 2019/12/25
 * 参考自：https://www.itcodemonkey.com/article/14097.html
 */
@Slf4j
//@Component
public class GlobalAuthFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("[全局拦截]进入Auth拦截器");
        ServerHttpRequest request = exchange.getRequest();
        HttpHeaders headers = request.getHeaders();
        List<String> list = headers.get("Authorization");
        if (list == null) {
            R result = R.fail("缺少token");
            String jsonStr = JSONUtil.toJsonStr(result);
            log.info("没有权限：{}", jsonStr);
            byte[] bytes = StrUtil.utf8Bytes(jsonStr);
            ServerHttpResponse response = exchange.getResponse();
            DataBuffer buffer = response.bufferFactory().wrap(bytes);
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
            return response.writeWith(Mono.just(buffer));
        } else {
            log.info("token验证通过:{}", JSONUtil.toJsonStr(list));
            return chain.filter(exchange);
        }
    }

    @Override
    public int getOrder() {
        return -1000;
    }
}
