package com.pubsub.demo.controller;

import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.integration.outbound.PubSubMessageHandler;
import com.pubsub.demo.publisher.PubsubOutboundGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1.0/publisher")
public class PublisherController {
    private static final String TOPIC_NAME = "pubsub-topic";
    @Autowired
    private PubsubOutboundGateway messagingGateway;

    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public String publishMessage(@RequestParam("message") String message) {
        messagingGateway.sendToPubsub(message);

        return message;
    }

    @Bean
    @ServiceActivator(inputChannel = "outputChannel")
    public MessageHandler messageSender(PubSubTemplate pubsubTemplate) {
        return new PubSubMessageHandler(pubsubTemplate, TOPIC_NAME);
    }
}
