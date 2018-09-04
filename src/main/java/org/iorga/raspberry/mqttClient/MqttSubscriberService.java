package org.iorga.raspberry.mqttClient;

import com.google.gson.Gson;
import org.eclipse.paho.client.mqttv3.*;
import org.iorga.raspberry.controllers.RaspberryController;
import org.iorga.raspberry.identitys.BrokerCredentials;
import org.iorga.raspberry.messages.MessageStaticStrings;
import org.iorga.raspberry.messages.MqttCustomMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class MqttSubscriberService implements MqttCallback{

    @Autowired
    private RaspberryController raspberryController;

    @Autowired
    private BrokerCredentials brokerInfo;

    private MqttClient mqttClientSubscriber;
    private MqttClient mqttClientPublisher;

    private Gson gson;

    public MqttSubscriberService() {
        this.gson = new Gson();
    }

    public void connectToBroker() throws MqttException {
        MqttConnectOptions conOpt = new MqttConnectOptions();
        conOpt.setKeepAliveInterval(60);
        conOpt.setCleanSession(true);

        this.mqttClientSubscriber = new MqttClient("tcp://" + brokerInfo.getBrokerIp() + ":" + brokerInfo.getBrokerPort(), brokerInfo.getMqttClientId());
        this.mqttClientSubscriber.setCallback(this);
        this.mqttClientSubscriber.connect(conOpt);

        System.out.println("Connected to broker!");

        this.mqttClientSubscriber.subscribe(brokerInfo.getAllSmartOutletTopic(), brokerInfo.getMqttQos());
        this.mqttClientSubscriber.subscribe(brokerInfo.getUniqueTopic(), brokerInfo.getMqttQos());

        sendMessage(new MqttCustomMessage("Conexiune reusita", MessageStaticStrings.MESSAGE_TYPE_HAS_CONNECTED).toJson());
    }

    public void sendMessage(String payload) throws MqttException {
        mqttClientPublisher = new MqttClient("tcp://" + brokerInfo.getBrokerIp() + ":" + brokerInfo.getBrokerPort(),  brokerInfo.getMqttClientId() + "-publisher");
        MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(true);

        mqttClientPublisher.connect(connOpts);

        MqttMessage message = new MqttMessage(payload.getBytes());
        message.setQos(brokerInfo.getMqttQos());
        mqttClientPublisher.publish(brokerInfo.getServerTopic(), message);
        mqttClientPublisher.disconnect();
    }


    @Override
    public void connectionLost(Throwable throwable) {
        System.out.println("Connection lost because: " + throwable);
        try {
            connectToBroker();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {

        String payload = String.format(new String(mqttMessage.getPayload()));
        System.out.print(payload);
        MqttCustomMessage recivedMessage = gson.fromJson(payload, MqttCustomMessage.class);
        System.out.println(" " + recivedMessage.getPayload());

        if(recivedMessage.getType().equals(MessageStaticStrings.MESSAGE_TYPE_PREZENTA)){
            MqttCustomMessage message = new MqttCustomMessage("I am online!", MessageStaticStrings.MESSAGE_TYPE_PREZENTA);
            sendMessage(message.toJson());
        }else if(recivedMessage.getType().equals(MessageStaticStrings.MESSAGE_TYPE_ACTION)){
            raspberryController.newActionFromServer(recivedMessage);
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }
}
