package org.iorga.raspberry.controllers;

import org.iorga.raspberry.messages.MqttCustomMessage;
import org.iorga.raspberry.mqttClient.MqttSubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class RaspberryController {

    @Autowired
    private MqttSubscriberService mqttSubscriberService;

    public RaspberryController() {
    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() throws Exception {
        mqttSubscriberService.connectToBroker();
    }

    public void newActionFromServer(MqttCustomMessage recivedMessage) {
        System.out.println("Action");
    }
}
