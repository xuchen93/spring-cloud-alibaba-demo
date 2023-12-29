package com.github.xuchen93.cloud.seata.feign;

import com.github.xuchen93.cloud.model.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "spring-cloud-alibaba-seata-service2")
public interface Service2Remote {


    @GetMapping("success")
    R<String> success();

    @GetMapping("fail")
    R<String> fail();
}
