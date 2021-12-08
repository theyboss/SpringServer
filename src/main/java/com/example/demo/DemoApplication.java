package com.example.demo;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Pong;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableScheduling
public class DemoApplication {

	public static void main(String[] args) {

		InfluxDB influxDB = InfluxDBFactory.connect("http://127.0.0.1:8086", "root", "root");

		influxDB.enableBatch(100, 200, TimeUnit.MILLISECONDS);

		influxDB.setDatabase("TEST");

		SpringApplication.run(DemoApplication.class, args);
	}
}
