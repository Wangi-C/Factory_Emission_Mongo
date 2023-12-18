import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import pkg.App;
import pkg.address.Factory;
import pkg.address.AddressServiceAPI;
import pkg.address.Coordinates;
import pkg.address.FactoryAddressExcelReader;
import pkg.consumer.Consumer;
import pkg.entity.People;
import pkg.producer.Producer;
import pkg.service.PeopleService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = App.class)
public class KafkaTest {
//    @Autowired
    private AddressServiceAPI service;
//    @Autowired
    private FactoryAddressExcelReader factoryAddress;
    @Autowired private Producer producer;

    @Test
    @DisplayName("insert json producer >> consumer")
    void insertJson() {
        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
            System.out.println("스케쥴러 테스트 : " + LocalDateTime.now().format(dateTimeFormatter));

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("time", LocalDateTime.now().format(dateTimeFormatter));
            jsonObject.put("age", 40);
            jsonObject.put("name", "posco_wangi");
            jsonObject.put("salary", 4000);

            producer.create(jsonObject.toString());

            Thread.sleep(30 * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
