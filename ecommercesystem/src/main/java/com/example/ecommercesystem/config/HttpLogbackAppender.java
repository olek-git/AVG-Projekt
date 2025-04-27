package com.example.ecommercesystem.config;

import ch.qos.logback.core.AppenderBase;
import ch.qos.logback.classic.spi.ILoggingEvent;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;

/**
 * Custom Logback Appender zum Senden von Logdaten an einen HTTP-Endpunkt.
 * 
 * Dieser Appender erfasst Logdaten und sendet sie in Form einer
 * HTTP-POST-Anfrage an einen konfigurierten
 * Endpunkt. Dies ermöglicht das zentrale Sammeln von Logdaten über HTTP,
 * beispielsweise in einem
 * Monitoring- oder Logging-System.
 */
public class HttpLogbackAppender extends AppenderBase<ILoggingEvent> {

    private String endpointUrl;

    /**
     * Setzt die URL des Endpunkts, an den die Logdaten gesendet werden.
     * 
     * @param endpointUrl Die URL des Ziel-Endpunkts
     */
    public void setEndpointUrl(String endpointUrl) {
        this.endpointUrl = endpointUrl;
    }

    /**
     * Verarbeitet das Logging-Ereignis und sendet die Logdaten an den
     * konfigurierten HTTP-Endpunkt.
     * 
     * @param eventObject Das Log-Ereignis, das verarbeitet wird
     */
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