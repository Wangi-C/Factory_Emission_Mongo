import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pkg.App;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootTest(classes = App.class)
public class KafkaTest {

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

            Thread.sleep(30 * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

//    @Test
    @DisplayName("json to Java Object")
    void convertJsonObject() throws Exception {
        String jsonData = "[\n" +
                "  {\n" +
                "    \"age\": 37,\n" +
                "    \"name\": \"wangi01\",\n" +
                "    \"salary\": 4000\n" +
                "  },\n" +
                "  {\n" +
                "    \"age\": 45,\n" +
                "    \"name\": \"wangi02\",\n" +
                "    \"salary\": 5000\n" +
                "  },\n" +
                "  {\n" +
                "    \"age\": 46,\n" +
                "    \"name\": \"wangi03\",\n" +
                "    \"salary\": 6000\n" +
                "  }\n" +
                "]";

        JSONArray jsonArray = new JSONArray(jsonData);
        System.out.println("jsonArray = " + jsonArray);

        ObjectMapper objectMapper = new ObjectMapper();
    }
}
