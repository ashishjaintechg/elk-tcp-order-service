# order-service
This generate logs and pushes to logstash in tcp mode
1. start elastic service
2. uncomment elastic host line from config/kibana.yml
elasticsearch.hosts: ["http://localhost:9200"]
3. start /bin/$ ./kibana
4. create config/logstash.conf
input {
  tcp {
    port => 5044
    codec => json

  }
}

output {
  elasticsearch {
    hosts => ["http://localhost:9200"]
    index => "micro-%{appName}"
    #user => "elastic"
    #password => "changeme"
  }
}
5. Run logstash /bin$ ./logstash -f ../config/logstash.conf
6. add log back dependency in pom 
 <dependency>
            <groupId>net.logstash.logback</groupId>
            <artifactId>logstash-logback-encoder</artifactId>
            <version>6.6</version>
        </dependency>
7. add logback-spring.xml file
<?xml version="1.0" encoding="UTF-8"?>
<configuration>
    <include resource="org/springframework/boot/logging/logback/base.xml"/>
    <appender name="logstash" class="net.logstash.logback.appender.LogstashTcpSocketAppender">
        <destination>localhost:5044</destination>
        <encoder class="net.logstash.logback.encoder.LoggingEventCompositeJsonEncoder">
            <providers>
                <mdc />
                <context />
                <logLevel />
                <loggerName />
                <pattern>
                    <pattern>
                        {
                        "appName": "order-service"
                        }
                    </pattern>
                </pattern>
                <threadName />
                <message />
                <logstashMarkers />
                <stackTrace />
            </providers>
        </encoder>
    </appender>
    <root level="info">
        <appender-ref ref="logstash" />
    </root>
</configuration>
8. Run spring boot application 8080
9. hit the url http://localhost:8080/order
10. check the index created in logstash micro-order-service
http://localhost:9200/_cat/indices
11. check index recieved from elastic search in kibana http://localhost:5601/
  GET /micro-order-service
 12. Do statemanagement -> index patterns -> create index -> micro-* for all microservices
 13. Discover -> select created index and explore yourself with different filter and visualization.
 
  
