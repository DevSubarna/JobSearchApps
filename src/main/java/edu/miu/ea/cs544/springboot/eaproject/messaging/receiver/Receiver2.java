package edu.miu.ea.cs544.springboot.eaproject.messaging.receiver;

import edu.miu.ea.cs544.springboot.eaproject.entities.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver2 {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @JmsListener(destination = "${springJms.cs544Queue}")
    public void receive(String message) {
        log.info("Received Job operations .... ");
        log.info("Receiver 2 .... ");
        log.info("Received message from sender via JMS ...." + message);
    }
}
