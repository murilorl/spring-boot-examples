package com.mrl.service;

import com.mrl.model.core.vendor.Vendor;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaService {

    private final KafkaTemplate<String, Vendor> kafkaTemplate;

    public void sendMessage(Vendor vendor, String topic) {
        ListenableFuture<SendResult<String, Vendor>> future = kafkaTemplate.send(topic, vendor.getAccountNumber(),
                vendor);

        future.addCallback(new ListenableFutureCallback<SendResult<String, Vendor>>() {

            @Override
            public void onSuccess(SendResult<String, Vendor> result) {
                log.info("Sent message=[{}] with offset=[{}]", vendor, result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(Throwable ex) {
                log.error("Unable to send message=[{}] due to [{}]", vendor, ex.getMessage());
            }
        });
    }
}
