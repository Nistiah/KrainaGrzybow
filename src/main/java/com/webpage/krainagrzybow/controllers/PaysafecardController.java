package com.webpage.krainagrzybow.controllers;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Controller
    public class PaysafecardController {

    @GetMapping("/api/payments/paysafecard")
    @ResponseBody
    public ResponseEntity<String> getPaysafecardPayment() {

        String username = "test_krainagrzybow";
        String apiKey = "B-qa2-0-643bcb38-0-302c021418a18aa417f4ed5909fc5f45333da4f834c80809021474c6a3fb3e43a898885c87fcc8eca00ef13dc577";
        String auth = username + ":" + apiKey;
        String authHeader = "Basic " + Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));

        //CHECK SERVICE STATUS
        Client client_check = ClientBuilder.newClient();
        Response response_check = client_check.target("https://api.test.paysafe.com/paymenthub/v1/monitor")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", authHeader)
                .get();

        String responseBody = response_check.readEntity(String.class);
        System.out.println("status: " + responseBody);
/*
        //CHECK PAYMENT METHODS
        response_check = client_check.target("https://api.test.paysafe.com/paymenthub/v1/paymentmethods?currencyCode=USD")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", authHeader)
                .get();

        System.out.println("methods: " + response_check.getStatus());
        responseBody = response_check.readEntity(String.class);
        System.out.println("methods: " + responseBody);


        Client client = ClientBuilder.newClient();
        Entity<String> payload = Entity.json("""
                {
                  "merchantRefNum": "49115d2d54fba7f6a0e8",
                  "transactionType": "PAYMENT",
                  "threeDs": {
                    "merchantUrl": "https://api.qa.paysafe.com/checkout/v2/index.html#/desktop",
                    "deviceChannel": "BROWSER",
                    "messageCategory": "PAYMENT",
                    "transactionIntent": "CHECK_ACCEPTANCE",
                    "authenticationPurpose": "PAYMENT_TRANSACTION",
                    "requestorChallengePreference": "NO_PREFERENCE",
                    "orderItemDetails": {
                      "preOrderItemAvailabilityDate": "2014-01-26",
                      "preOrderPurchaseIndicator": "MERCHANDISE_AVAILABLE",
                      "reorderItemsIndicator": "FIRST_TIME_ORDER",
                      "shippingIndicator": "SHIP_TO_BILLING_ADDRESS"
                    },
                    "purchasedGiftCardDetails": {
                      "amount": 1234,
                      "count": 1,
                      "currency": "USD"
                    },
                    "userAccountDetails": {
                      "addCardAttemptsForLastDay": 1,
                      "changedDate": "2010-01-26",
                      "changedRange": "DURING_TRANSACTION",
                      "createdDate": "2010-01-26",
                      "createdRange": "NO_ACCOUNT",
                      "passwordChangedDate": "2012-01-26",
                      "passwordChangedRange": "NO_CHANGE",
                      "paymentAccountDetails": {
                        "createdRange": "NO_ACCOUNT",
                        "createdDate": "2010-01-26"
                      },
                      "shippingDetailsUsage": {
                        "cardHolderNameMatch": true,
                        "initialUsageDate": "2014-01-26",
                        "initialUsageRange": "CURRENT_TRANSACTION"
                      },
                      "suspiciousAccountActivity": true,
                      "totalPurchasesSixMonthCount": 1,
                      "transactionCountForPreviousDay": 1,
                      "transactionCountForPreviousYear": 3,
                      "userLogin": {
                        "authenticationMethod": "NO_LOGIN",
                        "data": "Some up to 2048 bytes undefined data",
                        "time": "2014-01-26T10:32:28Z"
                      }
                    }
                  },
                  "card": {
                    "cardNum": "4000000000001026",
                    "cardExpiry": {
                      "month": 10,
                      "year": 2025
                    },
                    "cvv": "111",
                    "holderName": "Dilip"
                  },
                  "accountId": "1009688230",
                  "paymentType": "CARD",
                  "amount": 500,
                  "currencyCode": "USD",
                  "billingDetails": {
                    "nickName": "Home",
                    "street": "abcd",
                    "city": "defg",
                    "state": "AL",
                    "country": "US",
                    "zip": "94404"
                  },
                  "returnLinks": [
                    {
                      "rel": "default",
                      "href": "https://usgaminggamblig.com/payment/return/",
                      "method": "GET"
                    },
                    {
                      "rel": "on_completed",
                      "href": "https://usgaminggamblig.com/payment/return/success",
                      "method": "GET"
                    },
                    {
                      "rel": "on_failed",
                      "href": "https://usgaminggamblig.com/payment/return/failed",
                      "method": "GET"
                    }
                  ]
                }""");

        //Response response = client.target("https://private-anon-45dd7a376b-paysafeapicardpaymentsv1.apiary-mock.com/cardpayments/v1/accounts/123456789/verifications")
        Response response = client.target("https://api.test.paysafe.com/paymenthub/v1/paymenthandles")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", authHeader)
                .post(payload);

        System.out.println("status: " + response.getStatus());
        System.out.println("headers: " + response.getHeaders());
        responseBody = response.readEntity(String.class);
        System.out.println("body:" + responseBody);

 */
        return ResponseEntity.ok(responseBody);
    }
}
