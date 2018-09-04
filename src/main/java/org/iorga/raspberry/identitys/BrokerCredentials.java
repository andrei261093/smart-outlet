package org.iorga.raspberry.identitys;

import org.iorga.raspberry.utils.PropertiesLoader;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class BrokerCredentials {
    private String brokerIp;
    private String brokerPort;
    private String mqttClientId;
    private String allSmartOutletTopic;
    private String uniqueTopic;
    private String serverTopic;
    private int mqttQos;

    public BrokerCredentials() {
        HashMap properties = PropertiesLoader.getProperties("brokerIp", "brokerPort","mqttClientId", "allSmartOutletTopic","uniqueTopic", "serverTopic", "mqttQos");
        this.brokerIp = (String) properties.get("brokerIp");
        this.brokerPort = (String) properties.get("brokerPort");
        this.mqttClientId = (String) properties.get("mqttClientId");
        this.allSmartOutletTopic = (String) properties.get("allSmartOutletTopic");
        this.uniqueTopic = (String) properties.get("uniqueTopic");
        this.serverTopic = (String) properties.get("serverTopic");
        this.mqttQos = Integer.parseInt((String) properties.get("mqttQos"));
    }

    public String getBrokerIp() {
        return brokerIp;
    }

    public void setBrokerIp(String brokerIp) {
        this.brokerIp = brokerIp;
    }

    public String getBrokerPort() {
        return brokerPort;
    }

    public void setBrokerPort(String brokerPort) {
        this.brokerPort = brokerPort;
    }

    public String getMqttClientId() {
        return mqttClientId;
    }

    public void setMqttClientId(String mqttClientId) {
        this.mqttClientId = mqttClientId;
    }

    public String getAllSmartOutletTopic() {
        return allSmartOutletTopic;
    }

    public void setAllSmartOutletTopic(String allSmartOutletTopic) {
        this.allSmartOutletTopic = allSmartOutletTopic;
    }

    public String getUniqueTopic() {
        return uniqueTopic;
    }

    public void setUniqueTopic(String uniqueTopic) {
        this.uniqueTopic = uniqueTopic;
    }

    public String getServerTopic() {
        return serverTopic;
    }

    public void setServerTopic(String serverTopic) {
        this.serverTopic = serverTopic;
    }

    public int getMqttQos() {
        return mqttQos;
    }

    public void setMqttQos(int mqttQos) {
        this.mqttQos = mqttQos;
    }
}
