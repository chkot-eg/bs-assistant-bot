# BS Assistant Bot

Backend service built with EG Fabric Platform.

## Project Structure

```
bs-assistant-bot/
├── pom.xml                                 # Maven configuration
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── no/eg/bsassistantbot/
│   │   │       ├── BsAssistantBotApplication.java       # Main application class
│   │   │       ├── controller/
│   │   │       │   └── HelloController.java        # Sample REST controller
│   │   │       └── service/
│   │   │           └── ExternalApiService.java     # Sample service
│   │   └── resources/
│   │       └── application.properties              # Configuration
│   └── test/
│       └── java/
│           └── no/eg/bsassistantbot/
│               └── BsAssistantBotApplicationTests.java  # Test class
└── README.md
```

## Prerequisites

- Java 21 or higher
- Maven 3.x
- Access to EG Artifactory

## Build and Run

### Build the project:
```bash
mvn clean package
```

### Run the application:
```bash
mvn spring-boot:run
```

Or run the JAR:
```bash
java -jar target/bs-assistant-bot-1.0.0-SNAPSHOT.jar
```

### Test the application:
```bash
# Health check
curl http://localhost:8080/actuator/health

# Hello endpoint (may require authentication)
curl -H "Authorization: Bearer YOUR_JWT_TOKEN" http://localhost:8080/api/hello
```

## Configuration

Before running, update `src/main/resources/application.properties`:
- Replace `key.public` with the actual public key from the platform team
- Adjust `apps.accessManager.baseUrl` if needed

## What's Included

This service uses `@EnableEgAppsService` which provides:
- Simplified security configuration
- Service registration with Access Manager
- Backend authentication
- Logging with MDC

## Next Steps

1. Update configuration values in `application.properties`
2. Add your business logic in the service layer
3. Create additional REST controllers as needed
4. Implement your domain models and repositories
