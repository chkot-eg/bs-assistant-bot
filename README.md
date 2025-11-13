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
│       ├── java/
│       │   └── no/eg/bsassistantbot/
│       │       ├── BsAssistantBotApplicationTests.java  # Test class
│       │       └── TestRedisConfiguration.java          # Redis test config
│       └── resources/
│           └── application-test.properties         # Test configuration
└── README.md
```

## Prerequisites

- Java 21 or higher
- Maven 3.x
- Access to EG Artifactory
- Redis Server (optional for local development)

## Build and Run

### Build the project:
```bash
mvn clean package
```

### Run tests:
```bash
mvn test
```

### Run the application:
```bash
mvn spring-boot:run
```

Or run the JAR:
```bash
java -jar target/bs-assistant-bot-1.0.1-SNAPSHOT.jar
```

### Test the application:
```bash
# Health check
curl http://localhost:8080/actuator/health

# Hello endpoint (may require authentication)
curl -H "Authorization: Bearer YOUR_JWT_TOKEN" http://localhost:8080/api/hello
```

## Configuration

The application is configured in `src/main/resources/application.properties`:

### Key Configuration Properties:
```properties
# Server runs on port 8080
server.port=8080

# Circular references enabled (required by EG Fabric Platform)
spring.main.allow-circular-references=true

# Redis Configuration (for session management)
spring.data.redis.host=localhost
spring.data.redis.port=6379

# Security Key (change in production!)
global.securityKey=dev-secret-key-change-in-production

# Service Configuration
apps.service.id=bs-assistant-bot
apps.service.name=BS Assistant Bot
apps.service.register=false  # Set to true in production

# Spring Cloud Config Server
spring.config.import=optional:configserver:https://config-server.cloud.test.egapps.no
spring.cloud.config.fail-fast=false
spring.cloud.config.enabled=true
```

### Before Deploying to Production:
1. Replace `key.public` with the actual public key from the platform team
2. Update `global.securityKey` with a strong production key
3. Configure proper Redis connection details
4. Set `apps.service.register=true` to enable service registration
5. Adjust `apps.accessManager.baseUrl` if needed

## Dependencies

This project includes:
- **Spring Boot 3.5.5** - Core framework
- **Spring Boot Web** - REST API support
- **Spring Boot Actuator** - Health checks and monitoring
- **Spring Boot Data Redis** - Redis integration
- **Spring Boot AOP** - Aspect-oriented programming
- **Spring Cloud** - Cloud native features
- **Spring Cloud Config Client** - Centralized configuration management
- **EG Fabric Platform Client** - Platform integration
- **Jedis** - Redis client
- **Lombok** - Code generation (optional)

### Test Dependencies:
- **Spring Boot Starter Test** - Testing framework
- **Embedded Redis** - Redis for integration tests

## What's Included

This service uses `@EnableEgAppsService` which provides:
- Simplified security configuration
- Service registration with Access Manager
- Backend authentication
- Logging with MDC
- Tenant-aware features

## Features

✅ Health monitoring via Spring Boot Actuator
✅ Redis session management
✅ JWT-based authentication
✅ Circular dependency resolution
✅ Comprehensive test configuration
✅ Access Manager integration
✅ Centralized configuration via Spring Cloud Config Server

## Spring Cloud Config Server

This application is integrated with Spring Cloud Config Server for centralized configuration management.

### Configuration
The config server is configured as **optional**, meaning the application will start even if the config server is unavailable:

```properties
spring.config.import=optional:configserver:https://config-server.cloud.test.egapps.no
spring.cloud.config.fail-fast=false
spring.cloud.config.enabled=true
```

### How it works
1. On startup, the application attempts to connect to the config server
2. If available, it fetches additional configuration properties
3. Config server properties override local `application.properties`
4. If unavailable, the application uses local configuration only

### Disabling Config Server
To disable config server integration for local development:
```properties
spring.cloud.config.enabled=false
```

Or remove the `spring.config.import` line entirely.

## Monitoring Endpoints

With Spring Boot Actuator enabled, the following endpoints are available:

- **Health Check**: `GET http://localhost:8080/actuator/health`
- **Application Info**: `GET http://localhost:8080/actuator/info`

## Known Issues & Solutions

### Issue: Circular Dependency Error
**Solution**: Already configured with `spring.main.allow-circular-references=true`

### Issue: Redis Connection Errors in Development
**Solution**: Redis health check is disabled for local development. The app runs without Redis installed.

### Issue: NullPointerException in Service Registration
**Solution**: Expected in local development when Access Manager is not available. Set `apps.service.register=false` to suppress.

### Issue: Config Server Connection Timeout
**Solution**: The config server is configured as optional. If unavailable, the app will start with local configuration. To disable config server attempts, set `spring.cloud.config.enabled=false`.

## Local Development

For local development without external dependencies:
1. Redis health checks are disabled
2. Service registration is disabled by default
3. In-memory user service is used for authentication
4. Discovery client is optional

## Next Steps

1. Configure production values in `application.properties`
2. Add your business logic in the service layer
3. Create additional REST controllers as needed
4. Implement your domain models and repositories
5. Set up Redis server for session management
6. Configure proper security keys and certificates

## Troubleshooting

### Build fails with compilation errors
```bash
mvn clean install -U
```

### Application fails to start
Check the logs and ensure:
- Port 8080 is not already in use
- Java 21 is installed and configured
- All required properties are set in `application.properties`

### Tests fail
Embedded Redis is configured for tests. If tests fail, check:
- Port 6379 is not already in use during tests
- Test configuration in `src/test/resources/application-test.properties`
