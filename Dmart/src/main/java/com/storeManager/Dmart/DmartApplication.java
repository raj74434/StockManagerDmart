package com.storeManager.Dmart;

import com.storeManager.Dmart.models.StockProduct;
import com.storeManager.Dmart.models.Transection;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableTransactionManagement
public class DmartApplication {

	public static void main(String[] args) {
		SpringApplication.run(DmartApplication.class, args);
	}

//	It help in map one class to another class
	@Bean
	public ModelMapper createModelMapper(){
		return new ModelMapper();
	}



//	Created a bean of StockProduct
	@Bean
	public StockProduct createObjectOfStockProduct(){
		return new StockProduct();
	}



//	Created a bean of Transection
	@Bean
	public Transection createTransection(){
		return new Transection();
	}



}
