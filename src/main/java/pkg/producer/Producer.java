package pkg.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public Producer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void create() {
        kafkaTemplate.send("devwangi", "say hello~");
    }
}
