package com.suu.hppa.stocktradarapp.config;

import java.util.Scanner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CliAppConfig {
    @Bean
    public Scanner stdInScanner() {
        return new Scanner(System.in);
    }
}
