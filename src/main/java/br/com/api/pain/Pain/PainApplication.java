package br.com.api.pain.Pain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = "br.com.api.pain.Pain")
@EnableFeignClients
public class PainApplication {

	public static void main(String[] args) {
		SpringApplication.run(PainApplication.class, args);
	}

}
