package top.linqinhao.dockerdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
public class DockerDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DockerDemoApplication.class, args);
	}

	@RequestMapping("/user/find")
	public Object findUser(){
		Map<String, String> map = new HashMap<>();
		map.put("name", "linqinhao.top");
		map.put("age", "28");
		return map;
	}
}
