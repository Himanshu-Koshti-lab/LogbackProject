
**Add Dependency of Logback.**

```xml
                <dependency>
			<groupId>ch.qos.logback</groupId>
			<artifactId>logback-classic</artifactId>
			<version>1.5.3</version>
		</dependency>
		<dependency>
			<groupId>ch.qos.logback</groupId>
			<artifactId>logback-core</artifactId>
			<version>1.5.3</version>
		</dependency>
```

Then add logback.xml file to config the way of logging and log file creation. 

```xml
<configuration>
    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%d{HH:mm:ss.SSS} [%thread] %level %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>

    <root level="info">
        <appender-ref ref="STDOUT" />
    </root>
</configuration>
```

# Documentation
    https://logback.qos.ch/manual/index.html

## Add Actuator for fetching information about application and its internal memory usage.

```xml
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>
```

in application.properties you have to add manually expose endpoints.

    By Default it open 3 endpoints

```json
{
  "_links": {
     "self": {
          "href": "http://localhost:8080/actuator",
          "templated": false
          },
     "health": {
          "href": "http://localhost:8080/actuator/health",
          "templated": false
          },
     "health-path": {
          "href": "http://localhost:8080/actuator/health/{*path}",
          "templated": true
          }
            }
}
```

To expose all endpoints except shutdown : 
```yml
management.endpoints.web.exposure.include=*
management.endpoints.web.exposure.exclude=beans
```

If you want shutdown endpoint need to add

```xml
management.endpoint.shutdown.enabled=true
```
