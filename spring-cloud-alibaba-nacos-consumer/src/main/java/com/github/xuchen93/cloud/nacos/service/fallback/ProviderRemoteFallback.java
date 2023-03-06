package com.github.xuchen93.cloud.nacos.service.fallback;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.github.xuchen93.cloud.model.R;
import com.github.xuchen93.cloud.nacos.service.ProviderRemote;
import org.springframework.stereotype.Component;

@Component
public class ProviderRemoteFallback implements ProviderRemote {
	@Override
	public R<String> getProvider(String key) {
		return R.fail(StrUtil.format("{}-{}-{}", DateUtil.now(), key, "fallback"));
	}
}
