package com.ingenico.pay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;


/**
 * Created by mohamedtantawy on 10/17/17.
 */
@EnableAutoConfiguration
@ComponentScan("com.ingenico.pay")
@SpringBootApplication
public class EPayTransferApplication {

    public static void main(String[] args) {
        SpringApplication.run(EPayTransferApplication.class, args);
    }

}
