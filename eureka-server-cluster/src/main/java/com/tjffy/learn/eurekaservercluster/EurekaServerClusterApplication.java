package com.tjffy.learn.eurekaservercluster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


/**
 * @author jftang3
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerClusterApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServerClusterApplication.class, args);
	}

}

