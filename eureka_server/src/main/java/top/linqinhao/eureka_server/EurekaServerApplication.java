package top.linqinhao.eureka_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 官方文档：
 * https://cloud.spring.io/spring-cloud-static/spring-cloud-netflix/2.0.3.RELEASE/single/spring-cloud-netflix.html#spring-cloud-eureka-server
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServerApplication.class, args);
	}

}
