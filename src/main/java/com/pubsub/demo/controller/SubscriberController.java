package com.pubsub.demo.controller;

import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.integration.AckMode;
import com.google.cloud.spring.pubsub.integration.inbound.PubSubInboundChannelAdapter;
import com.google.cloud.spring.pubsub.support.BasicAcknowledgeablePubsubMessage;
import com.google.cloud.spring.pubsub.support.GcpPubSubHeaders;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1.0/subscriber")
public class SubscriberController {
    private static final String SUBSCRIPTION_NAME = "pubsub-subscription";
    private String message;

    @GetMapping("/hello")
    public String hello() {
        return "Hello World !";
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public String getMessage() {
        return String.format("Message from GCP: %s", message);
    }

    @Bean
    public PubSubInboundChannelAdapter messageChannelAdapter(@Qualifier("inputChannel") MessageChannel inputChannel, PubSubTemplate pubSubTemplate) {
        PubSubInboundChannelAdapter adapter =
                new PubSubInboundChannelAdapter(pubSubTemplate, SUBSCRIPTION_NAME);
        adapter.setOutputChannel(inputChannel);
        adapter.setAckMode(AckMode.MANUAL);

        return adapter;
    }

    @Bean
    public MessageChannel inputChannel() {
        return new DirectChannel();
    }

    @ServiceActivator(inputChannel = "inputChannel")
    public void receiveMessage(String payload) {
        System.out.println("============> Message Arrived ! ### " + payload);
        this.message = payload;
    }
}
