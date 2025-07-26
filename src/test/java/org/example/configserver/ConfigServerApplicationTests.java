package org.example.configserver;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
class ConfigServerApplicationTests {

    private final TestRestTemplate testRestTemplate;

    @Autowired
    public ConfigServerApplicationTests(TestRestTemplate testRestTemplate) {
        this.testRestTemplate = testRestTemplate;
    }

    @Test
    void contextLoads() {
        ResponseEntity<String> response = testRestTemplate.getForEntity("/gateway/default", String.class);
        Assertions.assertTrue(response.getStatusCode().is2xxSuccessful());
        Assertions.assertTrue(response.getBody().contains("8761"));
    }
}
