package com.lahtinen.cloud.service.frontend.port.queue;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.MessageAttributeValue;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lahtinen.cloud.service.frontend.domain.CommandPublisher;
import com.lahtinen.cloud.service.frontend.port.queue.command.Command;

import java.util.HashMap;

public class QueuePublisher implements CommandPublisher {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private final AmazonSQS client;
    private final String queueUrl;

    public QueuePublisher(String queueName) {
        client = AmazonSQSClientBuilder.defaultClient();
        queueUrl = client.getQueueUrl(queueName).getQueueUrl();
    }

    @Override
    public void publish(Command event) {
        try {
            var attributes = new HashMap<String, MessageAttributeValue>();
            attributes.put("type", new MessageAttributeValue().withStringValue(event.getClass().getSimpleName()));
            var message = new SendMessageRequest()
                    .withQueueUrl(queueUrl)
                    .withMessageBody(MAPPER.writeValueAsString(event))
                    .withMessageAttributes(attributes);
            client.sendMessage(message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
