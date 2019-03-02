package top.linqinhao.order_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
/**
 * 官方文档：https://cloud.spring.io/spring-cloud-static/Greenwich.RELEASE/single/spring-cloud.html#_spring_cloud_openfeign
 */
@EnableFeignClients
/**
 * 官方文档：https://cloud.spring.io/spring-cloud-static/spring-cloud-netflix/2.0.3.RELEASE/single/spring-cloud-netflix.html#_how_to_include_hystrix
 */
@EnableCircuitBreaker
@EnableHystrixDashboard
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

	/**
	 * 官方文档：https://cloud.spring.io/spring-cloud-static/Greenwich.RELEASE/single/spring-cloud.html#_looking_up_services
	 * @return
	 */
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
}
