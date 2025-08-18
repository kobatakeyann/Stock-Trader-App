package com.suu.hppa.summer_training;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.suu.hppa.summer_training.presentation.cli.controller.CliAppController;

@SpringBootApplication
public class SummerTrainingApplication implements ApplicationRunner {
	private final CliAppController appController;

	public SummerTrainingApplication(CliAppController appController) {
		this.appController = appController;
	}

	public static void main(String[] args) {
		SpringApplication.run(SummerTrainingApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) {
		this.appController.start();
	}
}
