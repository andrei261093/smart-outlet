package org.iorga.raspberry.identitys;

import org.iorga.raspberry.utils.PropertiesLoader;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class DeviceInfo {
    private String raspberryPiNumber;
    private String raspberryPiModel;
    private String raspberryPiDescription;

    public DeviceInfo() {
        HashMap properties = PropertiesLoader.getProperties("raspberryPiNumber", "raspberryPiModel", "descriere");
        this.raspberryPiNumber = (String) properties.get("raspberryPiNumber");
        this.raspberryPiModel = (String) properties.get("raspberryPiModel");
        this.raspberryPiDescription = (String) properties.get("raspberryPiDescription");
    }

    public String getRaspberryPiNumber() {
        return raspberryPiNumber;
    }

    public void setRaspberryPiNumber(String raspberryPiNumber) {
        this.raspberryPiNumber = raspberryPiNumber;
    }

    public String getRaspberryPiModel() {
        return raspberryPiModel;
    }

    public void setRaspberryPiModel(String raspberryPiModel) {
        this.raspberryPiModel = raspberryPiModel;
    }

    public String getRaspberryPiDescription() {
        return raspberryPiDescription;
    }

    public void setRaspberryPiDescription(String raspberryPiDescription) {
        this.raspberryPiDescription = raspberryPiDescription;
    }
}
