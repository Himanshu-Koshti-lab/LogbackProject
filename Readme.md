
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

# Prometheus Integration 

add dependency 
```xml
		<dependency>
			<groupId>io.micrometer</groupId>
			<artifactId>micrometer-registry-prometheus</artifactId>
		</dependency>
```

add application to prometheus server by add into its yml property file

```yaml
# my global config
global:
  scrape_interval: 5s # Set the scrape interval to every 15 seconds. Default is every 1 minute.
  evaluation_interval: 5s # Evaluate rules every 15 seconds. The default is every 1 minute.
  # scrape_timeout is set to the global default (10s).

# Load rules once and periodically evaluate them according to the global 'evaluation_interval'.
rule_files:
  # - "first_rules.yml"
  # - "second_rules.yml"

# A scrape configuration containing exactly one endpoint to scrape:
# Here it's Prometheus itself.
scrape_configs:
  # The job name is added as a label `job=<job_name>` to any timeseries scraped from this config.
  - job_name: "prometheus"

    # metrics_path defaults to '/metrics'
    # scheme defaults to 'http'.

    static_configs:
      - targets: ["localhost:9090"]

  - job_name: "Spring actuator"
    metrics_path: "/actuator/prometheus"
    scrape_interval: 3s
    static_configs:
      - targets: ["192.168.1.6:8080"]
```