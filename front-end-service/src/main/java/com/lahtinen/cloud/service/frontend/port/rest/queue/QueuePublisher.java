package com.lahtinen.cloud.service.frontend.port.rest.queue;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.MessageAttributeValue;
import com.amazonaws.services.sqs.model.QueueDoesNotExistException;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lahtinen.cloud.service.frontend.domain.EventPublisher;
import com.lahtinen.cloud.service.frontend.port.rest.queue.event.Event;

import java.util.HashMap;

// TODO: Remove SQS code duplication
public class QueuePublisher implements EventPublisher {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private final AmazonSQS client;
    private final String queueUrl;

    public QueuePublisher(String queueName) {
        client = AmazonSQSClientBuilder.standard()
                .withCredentials(new ProfileCredentialsProvider())  //TODO: get provider from config file (use ContainerProfile in prod)
                .withRegion(Regions.getCurrentRegion().getName())
                .build();
        queueUrl = createQueue(queueName);
    }

    private String createQueue(String queueName) {
        try {
            return client.getQueueUrl(queueName).getQueueUrl();
        } catch (QueueDoesNotExistException e) {
            return client.createQueue(queueName).getQueueUrl();
        }
    }

    public void publish(Event event) {
        try {
            final HashMap<String, MessageAttributeValue> attributes = new HashMap<>();
            attributes.put("type", new MessageAttributeValue().withStringValue(event.getClass().getSimpleName()));
            final SendMessageRequest message = new SendMessageRequest()
                    .withQueueUrl(queueUrl)
                    .withMessageBody(MAPPER.writeValueAsString(event))
                    .withMessageAttributes(attributes);
            client.sendMessage(message);
        } catch (Exception e) {
            // TODO: Finer grained handling.
            throw new RuntimeException(e);
        }
    }
}
