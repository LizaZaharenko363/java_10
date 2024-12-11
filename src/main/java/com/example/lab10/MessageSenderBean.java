package com.example.lab10;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.jms.*;
import jakarta.annotation.Resource;

@Named
@ApplicationScoped
public class MessageSenderBean {
    @Resource(lookup = "jms/__defaultConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(lookup = "queue/MessageQueue")
    private Queue demoQueue;

    private String lastMessageText;
    private String lastMessageSelector;

    public void sendMessage(String messageText, String selector) {
        try (JMSContext context = connectionFactory.createContext()) {
            Message message = context.createTextMessage(messageText);

            if (selector != null && !selector.isEmpty()) {
                if (selector.contains("type:")) {
                    String type = selector.split(":")[1];
                    message.setStringProperty("MessageType", type);
                }
            }

            context.createProducer().send(demoQueue, message);

            this.lastMessageText = messageText;
            this.lastMessageSelector = selector;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getLastMessageText() {
        return lastMessageText;
    }

    public String getLastMessageSelector() {
        return lastMessageSelector;
    }
}