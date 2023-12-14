import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pkg.App;
import pkg.producer.Producer;

//@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class KafkaTest {
    @Autowired private Producer producer;

    @Test
    void test() {
        producer.create();
    }
}
