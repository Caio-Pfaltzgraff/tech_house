package br.com.caioprojects.TechHouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class TechHouseApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechHouseApplication.class, args);
	}

}
