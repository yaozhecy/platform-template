package com.cy.platform.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);

        System.out.println("""
            (♥◠‿◠)ﾉﾞ  开发环境启动成功  ლ(´ڡ`ლ)ﾞ \s
             ________  _______   ___      ___ _______   ___       ________  ________        ___       __   ________  ________  ___  __    ________  ________  ________  ________  _______     \s
            |\\   ___ \\|\\  ___ \\ |\\  \\    /  /|\\  ___ \\ |\\  \\     |\\   __  \\|\\   __  \\      |\\  \\     |\\  \\|\\   __  \\|\\   __  \\|\\  \\|\\  \\ |\\   ____\\|\\   __  \\|\\   __  \\|\\   ____\\|\\  ___ \\    \s
            \\ \\  \\_|\\ \\ \\   __/|\\ \\  \\  /  / | \\   __/|\\ \\  \\    \\ \\  \\|\\  \\ \\  \\|\\  \\     \\ \\  \\    \\ \\  \\ \\  \\|\\  \\ \\  \\|\\  \\ \\  \\/  /|\\ \\  \\___|\\ \\  \\|\\  \\ \\  \\|\\  \\ \\  \\___|\\ \\   __/|   \s
             \\ \\  \\ \\\\ \\ \\  \\_|/_\\ \\  \\/  / / \\ \\  \\_|/_\\ \\  \\    \\ \\  \\\\\\  \\ \\   ____\\     \\ \\  \\  __\\ \\  \\ \\  \\\\\\  \\ \\   _  _\\ \\   ___  \\ \\_____  \\ \\   ____\\ \\   __  \\ \\  \\    \\ \\  \\_|/__ \s
              \\ \\  \\_\\\\ \\ \\  \\_|\\ \\ \\    / /   \\ \\  \\_|\\ \\ \\  \\____\\ \\  \\\\\\  \\ \\  \\___|      \\ \\  \\|\\__\\_\\  \\ \\  \\\\\\  \\ \\  \\\\  \\\\ \\  \\\\ \\  \\|____|\\  \\ \\  \\___|\\ \\  \\ \\  \\ \\  \\____\\ \\  \\_|\\ \\\s
               \\ \\_______\\ \\_______\\ \\__/ /     \\ \\_______\\ \\_______\\ \\_______\\ \\__\\          \\ \\____________\\ \\_______\\ \\__\\\\ _\\\\ \\__\\\\ \\__\\____\\_\\  \\ \\__\\    \\ \\__\\ \\__\\ \\_______\\ \\_______\\
                \\|_______|\\|_______|\\|__|/       \\|_______|\\|_______|\\|_______|\\|__|           \\|____________|\\|_______|\\|__|\\|__|\\|__| \\|__|\\_________\\|__|     \\|__|\\|__|\\|_______|\\|_______|
                                                                                                                                            \\|_________|                                      \s
            """);
    }
}
