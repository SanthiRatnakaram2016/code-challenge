package com.sensor.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

/*
 * This class is used to registers itself with the discovery-server at start-up.
 */
@EnableAutoConfiguration
@EnableDiscoveryClient
@Import(SensorWebApplication.class)
public class SensorServer {

	//2
    public static void main(String[] args) {
        // Will configure using accounts-server.yml
        System.setProperty("spring.config.name", "sensor-server");

        SpringApplication.run(SensorServer.class, args);
    }
}
