package org.example.p2p.sync;

import com.sun.messaging.ConnectionConfiguration;
import com.sun.messaging.ConnectionFactory;
import javax.jms.*;

public class Receiver {
    private ConnectionFactory factory = new ConnectionFactory();
    private JMSConsumer consumer;

    Receiver() {
        try (JMSContext context = factory.createContext("admin", "admin")) {
            factory.setProperty(ConnectionConfiguration.imqAddressList, "mq://127.0.0.1:7676, mq://127.0.0.1:7676");
            Destination messageQueue = context.createQueue("P2PSyncQueue");
            consumer = context.createConsumer(messageQueue);
            System.out.println("Listening...");
            Message message = consumer.receive();
            System.out.println("Message: " + message.getBody(org.example.p2p.Message.class));
            System.out.println("ToString(): " + message);

            while (true) {
                Thread.sleep(1000);
            }
        } catch (JMSException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new Receiver();
    }
}
