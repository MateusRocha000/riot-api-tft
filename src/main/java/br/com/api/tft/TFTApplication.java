package br.com.api.tft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = "br.com.api.pain.Pain")
@EnableFeignClients
public class TFTApplication {

	public static void main(String[] args) {
		SpringApplication.run(TFTApplication.class, args);
	}

}
