package org.iorga.raspberry.utils;

import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

public class PropertiesLoader {

    public static HashMap getProperties(String ... args){
        Properties prop = new Properties();
        InputStream input = null;

        HashMap hm = new HashMap();

        try {

        input = new FileInputStream("config.properties");

        prop.load(input);

        for(String property: args){
            hm.put(property, prop.getProperty(property));
        }


        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return hm;
    }
}
