package org.example.pub_sub.selectors;

import com.sun.messaging.ConnectionConfiguration;
import com.sun.messaging.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.TextMessage;

public class Sender {
    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        try (JMSContext context = factory.createContext("admin", "admin")) {
            factory.setProperty(ConnectionConfiguration.imqAddressList, "mq://127.0.0.1:7676, mq://127.0.0.1:7676");
            Destination destination = context.createTopic("PubSub");
            TextMessage message = context.createTextMessage();
            message.setText("Subscriptions");
            message.setStringProperty("symbol", "BSTU");
            context.createProducer().send(destination, message);
            System.out.println("Sent");
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
}
