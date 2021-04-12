package com.mrl;

import com.mrl.service.DatabaseService;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class KafkaToDatabaseApp {

	@Autowired
	private DatabaseService databaseService;

	public static void main(String[] args) {
		SpringApplication.run(KafkaToDatabaseApp.class, args);
	}

	// @EventListener(ApplicationReadyEvent.class)
	// public void consume() {
	// log.info("I'm here");

	// databaseService.insert("recordMetadata", "recordValue");
	// }

	// @Override
	// public void run(String... args) throws Exception {
	// log.info("I'm here");
	// databaseService.insert("recordMetadata", "recordValue");
	// }

	@KafkaListener(topics = "${kafka.topic.name}")
	public void consume(final ConsumerRecord<String, String> consumerRecord) {
		log.info("received {} {}", consumerRecord.key(), consumerRecord.value());
	}

}
