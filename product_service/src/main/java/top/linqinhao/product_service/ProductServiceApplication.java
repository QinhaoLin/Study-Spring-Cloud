package top.linqinhao.product_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 官方文档：
 * https://cloud.spring.io/spring-cloud-static/spring-cloud-netflix/2.0.3.RELEASE/single/spring-cloud-netflix.html#_service_discovery_eureka_clients
 */
@SpringBootApplication
public class ProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

}
