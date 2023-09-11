package com.techg.inventoryservice;

import com.techg.inventoryservice.model.Inventory;
import com.techg.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	//This bean will load data at time of application startup
	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository) {
		//create our supplier
		return args -> {
			Inventory inventory = new Inventory();
			inventory.setSkuCode("iphone_13");
			inventory.setQuantity(100);


			Inventory inventory1 = new Inventory();
			inventory1.setSkuCode("iphone_13_red");
			inventory1.setQuantity(0);

			inventoryRepository.save(inventory1);
			inventoryRepository.save(inventory);
		};
	}
}
