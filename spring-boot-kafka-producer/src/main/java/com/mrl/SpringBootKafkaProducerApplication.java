package com.mrl;

import com.mrl.model.core.vendor.Vendor;
import com.mrl.service.KafkaService;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class SpringBootKafkaProducerApplication {

	@Autowired
	private KafkaService kafkaService;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootKafkaProducerApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	private void produce() {

		Vendor vendor = new Vendor();
		vendor.setAccountNumber(RandomStringUtils.randomNumeric(10));
		vendor.setAuthorizationGroup(RandomStringUtils.randomAlphabetic(4));
		vendor.setIndustryKey(RandomStringUtils.randomAlphabetic(4));
		vendor.setName(RandomStringUtils.randomAlphabetic(35));

		log.info("---- Sending data to Kafka");
		kafkaService.sendMessage(vendor, "vendor");

	}

}
