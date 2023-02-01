package com.marcoadp.github.bancario;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class BancarioApplication {

    public static void main(String[] args) {
        SpringApplication.run(BancarioApplication.class, args);
    }

}
