package com.unbreakable.amqtest;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.net.URL;
import java.util.Scanner;

public class JMSDemo {
    public static void main(String[] args) throws Exception {
        // Connection configuration
        URL trustStoreURL = JMSDemo.class.getResource("/client.ts");
        System.setProperty("javax.net.ssl.trustStore", trustStoreURL.getPath());
        System.setProperty("javax.net.ssl.trustStorePassword", "password");

        String brokerURL = "ssl://localhost:61617";
        String user = "USERNAME";
        String password = "PASSWORD";

        ConnectionFactory factory = new org.apache.activemq.ActiveMQConnectionFactory(brokerURL);

        try (Connection connection = factory.createConnection(user, password)) {
            connection.start();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Queue destination = session.createQueue("test.queue");

            MessageProducer producer = session.createProducer(destination);

            Scanner scanner = new Scanner(System.in);
            System.out.println("Type 'send' to send a message or 'exit' to quit.");

            while (true) {
                String input = scanner.nextLine();
                if ("send".equalsIgnoreCase(input)) {
                    // Send a message
                    TextMessage message = session.createTextMessage("Hello, ActiveMQ Artemis!");
                    producer.send(message);
                    System.out.println("Sent message: " + message.getText());
                } else if ("exit".equalsIgnoreCase(input)) {
                    System.out.println("Exiting...");
                    break;
                } else {
                    System.out.println("Unrecognized command. Type 'send' to send a message or 'exit' to quit.");
                }
            }
        }
    }
}