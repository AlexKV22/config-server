package org.example.configserver;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ConfigServerApplicationTests {

    private final TestRestTemplate testRestTemplate;

    @Autowired
    public ConfigServerApplicationTests(TestRestTemplate testRestTemplate) {
        this.testRestTemplate = testRestTemplate;
    }

    @Test
    void contextLoadsGatewayTest() {
        ResponseEntity<String> response = testRestTemplate.getForEntity("/gateway/default", String.class);
        Assertions.assertTrue(response.getStatusCode().is2xxSuccessful());
        Assertions.assertTrue(response.getBody().contains("8082"));
    }

    @Test
    void contextLoadsEurekaTest() {
        ResponseEntity<String> response = testRestTemplate.getForEntity("/eureka-server/default", String.class);
        Assertions.assertTrue(response.getStatusCode().is2xxSuccessful());
        Assertions.assertTrue(response.getBody().contains("8761"));
    }

    @Test
    void contextLoadsNotificationServiceTest() {
        ResponseEntity<String> response = testRestTemplate.getForEntity("/notificationService/default", String.class);
        Assertions.assertTrue(response.getStatusCode().is2xxSuccessful());
        Assertions.assertTrue(response.getBody().contains("8081"));
    }

    @Test
    void contextLoadsUserServiceTest() {
        ResponseEntity<String> response = testRestTemplate.getForEntity("/userService/dev", String.class);
        Assertions.assertTrue(response.getStatusCode().is2xxSuccessful());
        Assertions.assertTrue(response.getBody().contains("8080"));
    }
}
