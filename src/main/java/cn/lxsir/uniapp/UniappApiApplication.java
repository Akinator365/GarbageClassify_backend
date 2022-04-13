package cn.lxsir.uniapp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;

/**
 * @author 罗祥
 */
@SpringBootApplication
@MapperScan(basePackages = {"cn.lxsir.uniapp.mapper"})
@EnableAsync

public class UniappApiApplication {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(UniappApiApplication.class, args);
	}

}

