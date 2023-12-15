package pkg;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pkg.producer.Producer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Slf4j
@EnableScheduling
@RequiredArgsConstructor
@Component
public class ScheduledRequest {

    private final Producer producer;
//    private final int rate = Integer.parseInt(System.getenv("schedule.rate"));

    @Scheduled(fixedRate = 15000)
    public void exec() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        System.out.println("스케쥴러 테스트 : " + LocalDateTime.now().format(dateTimeFormatter));

        String log = "스케쥴러 테스트 : " + LocalDateTime.now().format(dateTimeFormatter);

        /////////////////////////////

        try {
            // API 요청을 위한 기본 URL 설정
            StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B552584/cleansys/rltmMesureResult"); /*URL*/

            // 요청 파라미터 설정
            urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=N1RibrJkuyiy80TpD2gU5dvzYkv43X1E%2BinoM49YVpu26%2FpH2dAMrqGmcx3mY%2FOxjwjCXcERISeeNq5N2EKkug%3D%3D"); /*Service Key*/
//            urlBuilder.append("&" + URLEncoder.encode("areaNm","UTF-8") + "=" + URLEncoder.encode("경상북도", "UTF-8")); /*지역 명 LIKE 검색*/
//            urlBuilder.append("&" + URLEncoder.encode("factManageNm","UTF-8") + "=" + URLEncoder.encode("포스코", "UTF-8")); /*사업장의 이름 LIKE 검색*/
            urlBuilder.append("&" + URLEncoder.encode("type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*json, xml 중 택1*/

            // URL 객체 생성
            URL url = new URL(urlBuilder.toString());

            // HTTP 연결 설정
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");

            // API 호출 및 응답 코드 출력
            //System.out.println("Response code: " + conn.getResponseCode());

            BufferedReader rd;
            // 응답 코드가 200-300 사이인 경우 성공적인 응답을 받은 것으로 간주
            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                // 성공적인 응답을 받은 경우, InputStream을 이용하여 읽어오기
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                // 에러 응답을 받은 경우, ErrorStream을 이용하여 읽어오기
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }

            // 응답 내용을 StringBuilder에 저장
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }

            rd.close();
            conn.disconnect();

            // JSON 파싱
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(sb.toString());

            // items 내의 정보 출력
            JsonNode itemsNode = jsonNode.path("response").path("body").path("items");
            int cnt = 0;
            if (itemsNode.isArray()) {
                for (JsonNode item : itemsNode) {
                    for (Address address : Address.values()) {
                        if (item.path("fact_manage_nm").asText().equals(address.getCode())) {
                            ((ObjectNode) item).put("lat", address.getLatitude());
                            ((ObjectNode) item).put("lon", address.getLongitude());
//                            System.out.println(item.toString());

                            producer.create(item.toString());


                            cnt++;
                        }
                    }

                }
            }

//            producer.create(log);
            System.out.println("성공 : " + cnt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
