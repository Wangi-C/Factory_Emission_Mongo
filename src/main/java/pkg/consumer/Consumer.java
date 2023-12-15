package pkg.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

//@Component
public class Consumer {
    @KafkaListener(topics = "devwangi", groupId = "group_1")
    public void listener(Object object){
        System.out.println(object);
    }
}
