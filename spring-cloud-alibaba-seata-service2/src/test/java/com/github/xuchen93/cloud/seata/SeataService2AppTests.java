package com.github.xuchen93.cloud.seata;

import com.github.xuchen93.cloud.seata.entity.SeataT2;
import com.github.xuchen93.cloud.seata.service.SeataT2Service;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.util.List;

@SpringBootTest
class SeataService2AppTests {

	@Autowired
	List<DataSource> list;

	@SneakyThrows
	@Test
	void contextLoads() {
		list.forEach(i->{
			System.out.println(i.getClass());
			System.out.println(i);
			System.out.println("---------");
		});
	}

}
