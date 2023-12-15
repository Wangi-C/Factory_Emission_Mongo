import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pkg.App;
import pkg.address.Factory;
import pkg.address.AddressServiceAPI;
import pkg.address.Coordinates;
import pkg.address.FactoryAddressExcelReader;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = App.class)
public class KafkaTest {
    @Autowired private AddressServiceAPI service;
    @Autowired private FactoryAddressExcelReader factoryAddress;

    @Test
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
}
