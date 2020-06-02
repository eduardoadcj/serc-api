package com.eacj.sercapi;

import com.eacj.sercapi.api.security.service.AdminUserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class SercApiApplication {

    @Autowired
    private AdminUserRegisterService adminUserRegisterService;
    
    public static void main(String[] args) {
        SpringApplication.run(SercApiApplication.class, args);
    }
    
    @EventListener(ApplicationReadyEvent.class)
    public void onAfterSetup(){
        adminUserRegisterService.start();
    }

}
