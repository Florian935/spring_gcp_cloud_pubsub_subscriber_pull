package com.pubsub.demo.publisher;

import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway(defaultRequestChannel = "outputChannel")
public interface PubsubOutboundGateway {

    void sendToPubsub(String text);
}
