package no.eg.bsassistantbot;



import apps.platform.client.service.EnableEgAppsService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEgAppsService  // Enables backend service features
public class BsAssistantBotApplication {
    public static void main(String[] args) {
        SpringApplication.run(BsAssistantBotApplication.class, args);
    }
}
