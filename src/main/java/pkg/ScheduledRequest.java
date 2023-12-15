package pkg;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pkg.producer.Producer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Slf4j
@EnableScheduling
@RequiredArgsConstructor
//@Component
public class ScheduledRequest {

    private final Producer producer;

    @Scheduled(fixedRate = 5000)
    public void exec() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        System.out.println("스케쥴러 테스트 : " + LocalDateTime.now().format(dateTimeFormatter));

        String log = "스케쥴러 테스트 : " + LocalDateTime.now().format(dateTimeFormatter);

        producer.create(log);
    }
}
