package com.mrl;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics = "#{'${kafka.topic.name}'}")
    public void consume(final ConsumerRecord<String, String> consumerRecord) {
        log.info("received {} {}", consumerRecord.key(), consumerRecord.value());
    }

}
