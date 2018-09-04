package org.iorga.raspberry.messages;

import com.google.gson.Gson;
import org.iorga.raspberry.identitys.DeviceInfo;
import org.iorga.raspberry.identitys.ProjectInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Date;

public class MqttCustomMessage implements Serializable {

    private DeviceInfo deviceInfo;

    private ProjectInfo projectInfo;

    private String payload;
    private String type;
    private String destination;
    private Date datetime;

    public MqttCustomMessage(String payload, String type) {
        this.payload = payload;
        this.type = type;
        this.deviceInfo = new DeviceInfo();
        this.projectInfo = new ProjectInfo();
    }

    public String toJson(){
        datetime = new Date();
        return new Gson().toJson(this);
    }

    public DeviceInfo getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(DeviceInfo deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public ProjectInfo getProjectInfo() {
        return projectInfo;
    }

    public void setProjectInfo(ProjectInfo projectInfo) {
        this.projectInfo = projectInfo;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }
}
