package org.example.p2p.async;

import com.sun.messaging.ConnectionConfiguration;
import com.sun.messaging.ConnectionFactory;
import org.example.p2p.Message;
import javax.jms.*;
import java.util.Date;

public class Sender {
    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        try (JMSContext context = factory.createContext("admin", "admin")) {
            factory.setProperty(ConnectionConfiguration.imqAddressList, "mq://127.0.0.1:7676, mq://127.0.0.1:7676");

            Destination messageQueue = context.createQueue("P2PAsyncQueue");
            Message message = new Message("Hello async 1", new Date());
            ObjectMessage objectMessage = context.createObjectMessage(message);

            JMSProducer producer = context.createProducer();
            producer.send(messageQueue, objectMessage);
            System.out.println("Sent");
        } catch (JMSException ex) {
            throw new RuntimeException(ex);
        }
    }
}
