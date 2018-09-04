package org.iorga.raspberry.identitys;

import org.iorga.raspberry.utils.PropertiesLoader;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class ProjectInfo {
    private String projectCodeName = "smart-outlet";


    public ProjectInfo() {
        HashMap properties = PropertiesLoader.getProperties("projectCodeName");
        this.projectCodeName = (String) properties.get("projectCodeName");
    }

    public String getProjectCodeName() {
        return projectCodeName;
    }

    public void setProjectCodeName(String projectCodeName) {
        this.projectCodeName = projectCodeName;
    }
}
