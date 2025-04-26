package com.example.ecommercesystem;

import ch.qos.logback.core.AppenderBase;
import ch.qos.logback.classic.spi.ILoggingEvent;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;

public class HttpLogbackAppender extends AppenderBase<ILoggingEvent> {

    private String endpointUrl;

    public void setEndpointUrl(String endpointUrl) {
        this.endpointUrl = endpointUrl;
    }

    @Override
    protected void append(ILoggingEvent eventObject) {
        try {
            // Logdaten strukturieren
            Map<String, Object> logData = new HashMap<>();
            logData.put("timestamp", eventObject.getTimeStamp());
            logData.put("level", eventObject.getLevel().toString());
            logData.put("logger", eventObject.getLoggerName());
            logData.put("message", eventObject.getFormattedMessage());
            logData.put("thread", eventObject.getThreadName());

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(logData, headers);

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.postForEntity(endpointUrl, requestEntity, String.class);
        } catch (Exception e) {
            System.err.println("Fehler beim Senden des Logs: " + e.getMessage());
        }
    }
}