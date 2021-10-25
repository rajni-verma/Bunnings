package com.au.bunnings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CatalogBarcodeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatalogBarcodeServiceApplication.class, args);
		System.out.println("Catalog Application started...");
	}

}
