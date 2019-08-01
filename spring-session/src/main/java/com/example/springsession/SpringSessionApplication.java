package com.example.springsession;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.Scanner;

@SpringBootApplication
public class SpringSessionApplication {

    public static void main(String[] args) {
        //Scanner scanner = new Scanner(System.in);
        //String port = scanner.nextLine();
        new SpringApplicationBuilder(SpringSessionApplication.class).properties("server.port=8080").run(args);
    }

}
