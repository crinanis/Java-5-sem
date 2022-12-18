package org.example.pub_sub.priorities;

import com.sun.messaging.ConnectionConfiguration;
import com.sun.messaging.ConnectionFactory;
import javax.jms.*;

public class Receiver implements MessageListener {
    private final ConnectionFactory factory = new ConnectionFactory();
    private JMSConsumer consumer;

    Receiver() {
        try (JMSContext context = factory.createContext("admin", "admin", JMSContext.AUTO_ACKNOWLEDGE)) {
            factory.setProperty(ConnectionConfiguration.imqAddressList, "mq://127.0.0.1:7676, mq://127.0.0.1:7676");
            Destination destination = context.createTopic("PubSub");
            consumer = context.createConsumer(destination);
            System.out.println("Listening...");
            consumer.setMessageListener(this);

            while (true) {
                Thread.sleep(1000);
            }
        } catch (JMSException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onMessage(Message message) {
        try {
            System.out.println("Message: " + message.getBody(String.class));
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new Receiver();
    }
}
