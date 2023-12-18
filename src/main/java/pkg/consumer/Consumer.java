package pkg.consumer;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import pkg.entity.People;

@Component
public class Consumer {
    @Autowired
    MongoRepository mongoRepository;
    @KafkaListener(topics = "${spring.kafka.template.default-topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void listener(JSONObject jsonObject){
        People people = new People(null,
                                    (Integer) jsonObject.get("age"),
                                    (String) jsonObject.get("name"),
                                    (Integer) jsonObject.get("salary"));
        mongoRepository.save(people);

        System.out.println("Consumer >>" + jsonObject);
    }
}
