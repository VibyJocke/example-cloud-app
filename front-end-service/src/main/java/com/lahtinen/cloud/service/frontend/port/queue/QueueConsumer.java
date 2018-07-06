package com.lahtinen.cloud.service.frontend.port.queue;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.eventbus.EventBus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TEN_SECONDS;

public class QueueConsumer implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueueConsumer.class);
    private static final String FULLY_QUALIFIED_NAME = "com.lahtinen.cloud.service.frontend.port.queue.event.";
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private final AmazonSQS client;
    private final EventBus eventBus;
    private final String queueUrl;

    private volatile boolean running;
    private volatile boolean stopped;

    public QueueConsumer(EventBus eventBus, String queueName) {
        this.eventBus = eventBus;
        client = AmazonSQSClientBuilder.defaultClient();
        queueUrl = client.getQueueUrl(queueName).getQueueUrl();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            stopped = true;
            await().atMost(TEN_SECONDS).until(() -> !running);
        }));
    }

    @Override
    public void run() {
        running = true;

        while (!stopped) {
            try {
                final ReceiveMessageRequest request = new ReceiveMessageRequest()
                        .withQueueUrl(queueUrl)
                        .withMessageAttributeNames("type");
                final List<Message> messages = client.receiveMessage(request).getMessages();

                for (Message msg : messages) {
                    final String type = msg.getMessageAttributes().get("type").getStringValue();
                    final Class clazz = Class.forName(FULLY_QUALIFIED_NAME + type);
                    final String payload = msg.getBody();
                    eventBus.post(MAPPER.readValue(payload, clazz));
                }
            } catch (Exception e) {
                LOGGER.error("Failed to parse message", e);
            }
        }

        running = false;
    }
}
