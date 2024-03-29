package nl.hanze.demo2;

import java.util.Date;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import com.thoughtworks.xstream.XStream;

/**
 *
 * @author Pieter, refactored by Bart
 */


public class Producer {
    // URL of the JMS server. DEFAULT_BROKER_URL will just mean
    // that JMS server is on localhost
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;

    // Name of the queue we will be sending messages to
    private static String subject = "TESTQUEUE1";
//    private static String subject = "XML_Bericht";
    
    private Session session;
    private Connection connection;
    
    public Producer() {
    }
    
    public void demoMethod() {
    	try {
    		createConnection();
    		sendPerson();
            connection.close();
    	} catch (JMSException e) {
    		e.printStackTrace();
    	}
    }
    
    
    private void sendPerson() throws JMSException {
    	Person p = new Person ("Erk", "ERPI", "erk@gmail.com",24);
    	XStream xstream = new XStream();
    	xstream.alias("Person", Person.class);
    	String xml = xstream.toXML(p);
    	sendTextMessage(xml);
    }
    
    
    private void createConnection() throws JMSException {
   	 // Getting JMS connection from the server and starting it
       ConnectionFactory connectionFactory =
           new ActiveMQConnectionFactory(url);
       connection = connectionFactory.createConnection();
       connection.start();

       // JMS messages are sent and received using a Session. We will
       // create here a non-transactional session object. If you want
       // to use transactions you should set the first parameter to 'true'
       session = connection.createSession(false,
           Session.AUTO_ACKNOWLEDGE);
    }
    
    
    private void sendTextMessage(String themessage) throws JMSException {
    	System.out.println("Producer starting message: " + new Date());
        // Destination represents here our queue 'TESTQUEUE' on the
        // JMS server. You don't have to do anything special on the
        // server to create it, it will be created automatically.
        Destination destination = session.createQueue(subject);

        // MessageProducer is used for sending messages (as opposed
        // to MessageConsumer which is used for receiving them)
        MessageProducer producer = session.createProducer(destination);

        // We will send a small text message saying 'Hello' in Japanese
        TextMessage msg = session.createTextMessage(themessage);

        // Here we are sending the message!
        producer.send(msg);
        System.out.println("Sent message '" + msg.getText() + "'");
    }
    
    

    public static void main(String[] args) {
    	Producer producer = new Producer();
    	producer.demoMethod();
    }
}
