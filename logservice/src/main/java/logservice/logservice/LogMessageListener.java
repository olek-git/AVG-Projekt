package logservice.logservice;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class LogMessageListener {

    private static final String LOG_FILE = "all-logs.txt";

    @RabbitListener(queues = "log.queue")
    public void receive(String message) {
        try (FileWriter fw = new FileWriter(LOG_FILE, true)) {
            fw.write(LocalDateTime.now() + " - " + message + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}