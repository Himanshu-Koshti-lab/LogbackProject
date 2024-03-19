package com.SpringLog;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringLogApplication {

	Logger logger = LogManager.getLogger("SpringLogApplication");

	public static void main(String[] args) {
		SpringApplication.run(SpringLogApplication.class, args);
	}
	@GetMapping("/ping")
	public String ping(){
		for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            logger.info("inside ping info Function");
			logger.debug("inside ping debug Function");
			logger.warn("inside ping warn Function");
			logger.error("inside ping error Function");
		}

		return "pong";
	}

}
