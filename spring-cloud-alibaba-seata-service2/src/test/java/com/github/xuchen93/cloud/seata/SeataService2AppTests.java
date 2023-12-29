package com.github.xuchen93.cloud.seata;

import com.github.xuchen93.cloud.seata.entity.SeataT2;
import com.github.xuchen93.cloud.seata.service.SeataT2Service;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SeataService2AppTests {

	@Autowired
	SeataT2Service service;

	@SneakyThrows
	@Test
	void contextLoads() {
//		service.save(new SeataT2());
		service.save(new SeataT2(){{
			setIntV1(5);
			setVarV1("var1111");
		}});

	}

}
