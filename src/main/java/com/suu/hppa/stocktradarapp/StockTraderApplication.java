package com.suu.hppa.stocktradarapp;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.suu.hppa.stocktradarapp.presentation.cli.controller.CliAppController;

@SpringBootApplication
public class StockTraderApplication implements ApplicationRunner {
	private final CliAppController appController;

	public StockTraderApplication(CliAppController appController) {
		this.appController = appController;
	}

	public static void main(String[] args) {
		SpringApplication.run(StockTraderApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) {
		this.appController.start();
	}
}
