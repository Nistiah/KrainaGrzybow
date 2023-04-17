package com.webpage.krainagrzybow.controllers;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class PaysafecardController {

    @GetMapping("/api/payments/paysafecard")
    @ResponseBody
    public String getPaysafecardPayment() {
        String apiUrl = "https://api.test.paysafe.com/paymenthub";
        String username = "test_krainagrzybow";
        String apiKey = "B-qa2-0-643bcb38-0-302c021418a18aa417f4ed5909fc5f45333da4f834c80809021474c6a3fb3e43a898885c87fcc8eca00ef13dc577";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(username, apiKey);

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.GET, requestEntity, String.class);

        return response.getBody();
    }
}
