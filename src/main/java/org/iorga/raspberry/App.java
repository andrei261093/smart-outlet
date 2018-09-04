package org.iorga.raspberry;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PreDestroy;

@SpringBootApplication
public class App {
    public static void main(String[] args){
        SpringApplication.run(App.class, args);
    }

    @PreDestroy
    public void onDestroy(){
        System.out.println("Se inchide");
    }
}
