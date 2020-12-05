package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;

@EnableScheduling
@SpringBootApplication
public class TestGetRateBot1Application {

	public static void main(String[] args) {SpringApplication.run(TestGetRateBot1Application.class, args);}
}
