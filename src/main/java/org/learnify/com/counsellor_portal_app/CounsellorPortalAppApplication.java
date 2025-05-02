package org.learnify.com.counsellor_portal_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CounsellorPortalAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(CounsellorPortalAppApplication.class, args);
    }

    public void sayHello(){
        System.out.println("Hello World");
    }

}
