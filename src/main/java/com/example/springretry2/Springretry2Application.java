package com.example.springretry2;

import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.ConnectException;

@SpringBootApplication
@EnableRetry
public class Springretry2Application {
    public static void main(String[] args) {
        SpringApplication.run(Springretry2Application.class, args);
    }
}

@RestController
class TestRetryControllers {
    @Autowired
    TestRetry testRetry;

    @GetMapping("/hello")
    public String hello(){
        try {
            testRetry.testRetry();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "anc";
    }
}


class ToRetrun {
    private String test;

    public String getTest() {
        return test;
    }
}

@Service
class TestRetry {
     @Retryable(maxAttempts=9, backoff=@Backoff(delay = 2000))
     public void testRetry() throws Exception {
        System.out.println("try!");
        throw new Exception();
      }
}


