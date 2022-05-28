package com.example.laboratory_work;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void ReturnDefaultMessage() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/triangle?first=0&second=5&third=6",
                String.class)).contains("Negative numbers and zero are not allowed. They can not be triangle sites values.");
    }
    @Test
    public void ReturnEmpty() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/triangle?first=-4&second=5&third=6",
                String.class)).contains("Negative numbers and zero are not allowed. They can not be triangle sites values.");
    }
    @Test
    public void ReturnBadWord() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/triangle?first=4&second=4&third=8",
                String.class)).contains("This triangle does not exist. Or it is not triangle, but the line :)");
    }
}
