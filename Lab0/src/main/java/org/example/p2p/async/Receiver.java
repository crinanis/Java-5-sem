package org.example.p2p.async;

import com.sun.messaging.ConnectionConfiguration;
import com.sun.messaging.ConnectionFactory;
import javax.jms.*;

public class Receiver implements MessageListener {
    private ConnectionFactory factory = new ConnectionFactory();
    private JMSConsumer consumer;
    Receiver() {
        try (JMSContext context = factory.createContext("admin", "admin")) {
            factory.setProperty(ConnectionConfiguration.imqAddressList, "mq://127.0.0.1:7676, mq://127.0.0.1:7676");
            Destination messageQueue = context.createQueue("P2PAsyncQueue");
            consumer = context.createConsumer(messageQueue);
            consumer.setMessageListener(this);
            System.out.println("Listening...");

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
            System.out.println("Message: " + message.getBody(org.example.p2p.Message.class));
            System.out.println("ToString(): " + message);
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new Receiver();
    }
}
