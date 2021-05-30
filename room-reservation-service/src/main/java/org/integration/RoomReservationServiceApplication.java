package org.integration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class RoomReservationServiceApplication {
    //with Feign we automatically get Ribbon
    public static void main(String[] args) {
        // System properties can be set as command line arguments too
        System.setProperty("archaius.configurationSource.additionalUrls", "classpath:other-config-dir/extra.properties");
        System.setProperty("archaius.configurationSource.defaultFileName", "other.properties");
        SpringApplication.run(RoomReservationServiceApplication.class, args);
    }

}
