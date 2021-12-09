package com.epam.application;


import com.epam.user_interface.HomeMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.epam")
public class Application implements ApplicationRunner
{
    @Autowired
    HomeMenu homeMenu;

    public static void main(String[] args)
    {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception
    {
        homeMenu.showHomeUI();
    }
}