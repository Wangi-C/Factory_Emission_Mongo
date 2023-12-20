package pkg.consumer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import pkg.entity.FactoryEmission;

@Component
public class Consumer {
    @Autowired
    MongoRepository mongoRepository;

    @KafkaListener(topics = "${spring.kafka.template.default-topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void listener(JSONObject jsonObject) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        FactoryEmission factoryEmission = objectMapper.readValue(jsonObject.toString(), new TypeReference<FactoryEmission>() {});

        mongoRepository.save(factoryEmission);
//        System.out.println(jsonObject.toString());
    }
}
