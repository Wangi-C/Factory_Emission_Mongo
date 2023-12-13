package pkg;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Slf4j
@EnableScheduling
@Component
public class ScheduledRequest {

    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;


    public ScheduledRequest() {
        this.restTemplate = new RestTemplate();
        this.mapper = new ObjectMapper();
    }

    @Scheduled(fixedRate = 300000)
    public void exec() {
        try {

            for(City city: City.values()){


                String url = "http://openapi.seoul.go.kr:8088/6d6277526d61736138366457717851/json/citydata_ppltn/1/1/"+city.getCode();

                ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

                String jsonResponse = response.getBody();

                JsonNode rootNode = mapper.readTree(jsonResponse);

                ((ObjectNode) rootNode).put("lat", city.getLatitude());
                ((ObjectNode) rootNode).put("lon", city.getLongitude());

                log.info(rootNode.toString());

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
