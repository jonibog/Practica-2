package main2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
        System.out.println("Aplicación Spring Boot iniciada correctamente.");
    }
    @RestController
    @RequestMapping("/api")
    public class TestController {

        @GetMapping("/hello")
        public String hello() {
            return "¡Conexión HTTPS exitosa!";
        }
    }
}
