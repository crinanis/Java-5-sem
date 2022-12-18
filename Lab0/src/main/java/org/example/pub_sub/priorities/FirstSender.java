package org.example.pub_sub.priorities;

import com.sun.messaging.ConnectionConfiguration;
import com.sun.messaging.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.JMSException;

public class FirstSender {
    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        try (JMSContext context = factory.createContext("admin", "admin")) {
            factory.setProperty(ConnectionConfiguration.imqAddressList, "mq://127.0.0.1:7676, mq://127.0.0.1:7676");
            Destination destination = context.createTopic("PubSub");
            while (true) {
                System.out.println("Sent");
                context.createProducer().setPriority(8).send(destination, "Priority 8");
                Thread.sleep(1000);
            }
        } catch (JMSException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
