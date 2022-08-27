package edu.miu.ea.cs544.springboot.eaproject.messaging.sender;

import edu.miu.ea.cs544.springboot.eaproject.entities.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

@Component
public class Sender {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Value(value = "${springJms.cs544Queue}")
    private String queueName;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public void send1(String message) {
        log.info("Job Operations .... ");
        log.info("Sending message to receivers via JMS ....");
        jmsTemplate.convertAndSend(this.queueName,message);
    }
}
