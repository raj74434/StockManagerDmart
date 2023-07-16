package com.storeManager.Dmart;

import com.storeManager.Dmart.models.StockProduct;
import com.storeManager.Dmart.models.Transection;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;

@SpringBootApplication
@EnableTransactionManagement
public class DmartApplication {

	public static void main(String[] args) {
		SpringApplication.run(DmartApplication.class, args);
	}

	@Bean
	public ModelMapper createModelMapper(){
		return new ModelMapper();
	}

	@Bean
	public StockProduct createObjectOfStockProduct(){
		return new StockProduct();
	}

	@Bean
	public Transection createTransection(){
		return new Transection();
	}

}
