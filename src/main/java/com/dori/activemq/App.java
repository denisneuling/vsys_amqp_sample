package com.dori.activemq;

import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.apache.activemq.command.ActiveMQDestination;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;

import org.apache.log4j.Logger;

/**
 * Hello world!
 *
 */
public class App
{
    protected static Logger log = Logger.getLogger(App.class);
    public static void main( String[] args )
    {
    	ApplicationContext context = new ClassPathXmlApplicationContext("spring/jms/jms-context.xml");

		JmsTemplate template = (JmsTemplate) context.getBean("jmsTemplate");
		ActiveMQDestination destination = (ActiveMQDestination) context.getBean("destination");
		ActiveMQDestination destination2 = (ActiveMQDestination) context.getBean("destination2");

		// sending a message
		log.info("Sending message 1");
		template.convertAndSend(destination, "Hello Glorious Messaging World! 1");
		log.info("Sending message 2");
		template.convertAndSend(destination2, "Hello Glorious Messaging World! 2");

        // receiving a message
		log.info("Receiving message 1");
		Object msg = template.receive(destination);
		log.info("Receiving message 2");
		Object msg2 = template.receive(destination2);
		if (msg instanceof TextMessage) {
			try {
				log.info("Message 1 :"+((TextMessage) msg).getText());
			} catch (JMSException e) {
				System.out.println(e);
			}
		}
		if (msg2 instanceof TextMessage) {
			try {
				log.info("Message 2 :"+((TextMessage) msg2).getText());
			} catch (JMSException e) {
				System.out.println(e);
			}
		}
    }
}
