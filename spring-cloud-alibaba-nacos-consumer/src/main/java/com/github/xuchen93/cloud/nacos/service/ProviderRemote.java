package com.github.xuchen93.cloud.nacos.service;

import com.github.xuchen93.cloud.model.R;
import com.github.xuchen93.cloud.nacos.service.fallback.ProviderRemoteFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "nacos-provider", fallback = ProviderRemoteFallback.class)
public interface ProviderRemote {


	@GetMapping("getProvider")
	R<String> getProvider(@RequestParam("key") String key);
}
