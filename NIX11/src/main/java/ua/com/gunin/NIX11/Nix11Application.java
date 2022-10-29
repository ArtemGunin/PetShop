package ua.com.gunin.NIX11;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Nix11Application {

    public static void main(String[] args) {
//        ConfigurableApplicationContext context =
                SpringApplication.run(Nix11Application.class, args);
//        PasswordEncoder encoder = context.getBean(PasswordEncoder.class);
//        System.out.println(encoder.encode("pass"));
    }
}
