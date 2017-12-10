package fi.kajstrom.repmaxtracker.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.web.client.TestRestTemplate;

public class ApiTest {
    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate restTemplate;

    String makeUrl(String queryPath) {
        return "http://localhost:" + port + queryPath;
    }
}
