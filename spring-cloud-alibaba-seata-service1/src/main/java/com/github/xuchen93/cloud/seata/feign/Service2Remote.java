package com.github.xuchen93.cloud.seata.feign;

import com.github.xuchen93.cloud.model.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "spring-cloud-alibaba-seata-service2")
public interface Service2Remote {


    @PostMapping("success")
    R<String> success();

    @PostMapping("fail")
    R<String> fail();

    @PostMapping("test")
    R<String> test();
}
