package com.example.lab10;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.jms.*;
import jakarta.annotation.Resource;

@Named
@ApplicationScoped
public class MessageReceiverBean {
    @Resource(lookup = "jms/__defaultConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(lookup = "queue/MessageQueue")
    private Queue demoQueue;

    private String lastReceivedMessage;
    private String lastUsedSelector;

    public void receiveMessageWithSelector(String selector) {
        try (JMSContext context = connectionFactory.createContext()) {
            JMSConsumer consumer;
            if (selector != null && !selector.isEmpty()) {
                consumer = context.createConsumer(demoQueue, selector);
                lastUsedSelector = selector;
            } else {
                consumer = context.createConsumer(demoQueue);
                lastUsedSelector = "No selector";
            }

            Message message = consumer.receive(5000);

            if (message == null) {
                lastReceivedMessage = "No message received within the time frame or no matching message for selector.";
            } else if (message instanceof TextMessage textMessage) {
                lastReceivedMessage = textMessage.getText();
            }
        } catch (Exception e) {
            e.printStackTrace();
            lastReceivedMessage = "Error receiving message: " + e.getMessage();
        }
    }



    public String getLastReceivedMessage() {
        return lastReceivedMessage;
    }

    public String getLastUsedSelector() {
        return lastUsedSelector;
    }
}