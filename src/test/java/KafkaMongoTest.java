import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import pkg.App;
import pkg.address.AddressServiceAPI;
import pkg.address.Coordinates;
import pkg.address.Factory;
import pkg.address.FactoryAddressExcelReader;
import pkg.entity.People;
import pkg.producer.Producer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@DataMongoTest
@ContextConfiguration(classes = App.class)
public class KafkaMongoTest {
//    @Autowired
    private AddressServiceAPI service;
//    @Autowired
    private FactoryAddressExcelReader factoryAddress;
    @Autowired private MongoRepository repository;
    @Autowired private Producer producer;

//    @Test
    void geoTest() {
        ArrayList<Factory> factories = factoryAddress.readExcel();
        service.setFactoryList(factories);

        List<Coordinates> coordinates = service.getCoordinate();
        int i = 1;
        for (Coordinates coordinate : coordinates) {
            System.out.println("공장" + i + coordinate + ",");
            i++;
        }
    }

//    @Test
    @DisplayName("gdgd")
    void mongoConnectTest() {
        List<People> peopleList = repository.findAll();
        for (People people : peopleList) {
            System.out.println(people);
        }
    }

//    @Test
    @DisplayName("insert Test")
    @Transactional
    void insertDataTest() {
        People people = new People(null, 34, "hello", 123);
        Object save = repository.save(people);

        Assertions.assertEquals(repository.count(), 8);
        repository.delete(save);
    }

    @Test
    @DisplayName("insert json producer >> consumer")
    void insertJson() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        System.out.println("스케쥴러 테스트 : " + LocalDateTime.now().format(dateTimeFormatter));

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("time", LocalDateTime.now().format(dateTimeFormatter));
        jsonObject.put("age", 40);
        jsonObject.put("name", "posco");
        jsonObject.put("salary", 4000);

        producer.create(jsonObject.toString());
    }
}
