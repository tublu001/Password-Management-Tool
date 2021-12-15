package com.epam.application;


import com.epam.user_interface.HomeMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@ComponentScan(basePackages = "com.epam")
@EnableJpaRepositories(basePackages="com.epam")
@EnableTransactionManagement
@EntityScan(basePackages="com.epam")

public class Application
{
    public static void main(String[] args)
    {
//        MySQL_DB.initialize();
        SpringApplication.run(Application.class, args);
    }


//    @Bean(name="entityManagerFactory")
//    public LocalSessionFactoryBean sessionFactory() {
//        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//
//        return sessionFactory;
//    }

//    @Bean
//    public EntityManagerFactory entityManagerFactory(){
//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu");
//        return entityManagerFactory;
//    }
}