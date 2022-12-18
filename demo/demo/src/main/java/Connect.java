import com.sun.messaging.ConnectionConfiguration;
import com.sun.messaging.ConnectionFactory;

import javax.jms.*;
public class Connect {
    Connect(String message) {
        ConnectionFactory factory = new ConnectionFactory();
        try ( JMSContext context = factory.createContext("admin", "admin", JMSContext.AUTO_ACKNOWLEDGE)) {

    factory.setProperty(ConnectionConfiguration.imqAddressList,
                   "mq://127.0.0.1:7676,mq://127.0.0.1:7676");
            JMSProducer producer =context.createProducer();
            Destination Topic =  context.createTopic("topicDestination");
            String message1 = message;
            producer.send(Topic, message1);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public static void main(String[] args) {
        new Connect("Hello" );
    }
}


