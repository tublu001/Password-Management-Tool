package com.epam.application;


import com.epam.repository.MySQL_DB;
import com.epam.user_interface.HomeMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@ComponentScan(basePackages = "com.epam")
//public class Application implements ApplicationRunner
public class Application
{
//    @Autowired
//    HomeMenu homeMenu;

    public static void main(String[] args)
    {
        MySQL_DB.initialize();
        SpringApplication.run(Application.class, args);
    }

//    @Override
//    public void run(ApplicationArguments args) throws Exception
//    {
////        homeMenu.showHomeUI();
//    }
}