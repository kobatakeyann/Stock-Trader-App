package com.suu.hppa.summer_training;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.suu.hppa.summer_training.presentation.cli.view.HomeMenuView;

@SpringBootApplication
public class SummerTrainingApplication implements ApplicationRunner {
	private final HomeMenuView homeMenuView;

	public SummerTrainingApplication(HomeMenuView homeMenuView) {
		this.homeMenuView = homeMenuView;
	}

	public static void main(String[] args) {
		SpringApplication.run(SummerTrainingApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) {
		this.homeMenuView.show();
	}
}
