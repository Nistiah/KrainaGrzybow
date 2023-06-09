package com.webpage.krainagrzybow.controllers;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.json.JSONArray;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.json.JSONObject;

import javax.xml.stream.util.EventReaderDelegate;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Controller
    public class PaysafecardController {

    static String BackupRefnum = "1674816477305";

    static String s_username = "test_krainagrzybow";
    static String s_apiKey = "B-qa2-0-643bcb38-0-302c021418a18aa417f4ed5909fc5f45333da4f834c80809021474c6a3fb3e43a898885c87fcc8eca00ef13dc577";
    String merchantRefNum = " ";
    static int amount = 100;

    @GetMapping(value = "paysafecard/{refNum}")
    @ResponseBody
    public ResponseEntity<String> getPaysafecardPayment(@PathVariable String refNum) {

        merchantRefNum = refNum;

        String empty = " ";
        if( merchantRefNum.compareTo( empty) == 0)
            return ResponseEntity.ok( "missingRefNum");

        //CREATE AUTH HEADER
        String authHeader = GenerateAuthHeader();

        //CHECK SERVICE STATUS
        Send_CheckStatus( authHeader);

        //GET PAYMENT HANDLE
        String link = Send_RequestPaymentHandle( authHeader);

        //RETURN
        System.out.println( "Redirect link: " + link);
        return ResponseEntity.ok( link);

        /*
        //PAYMENT
        String paymentHandleToken = ReadPaymentHandleToken( response);
        response = Send_RequestPayment( authHeader, paymentHandleToken);
        */

    }

    JSONObject PaymentHandleCreator( String merchantRefNum, int amount, String customerId)
    {
        JSONObject jsonBody = new JSONObject();

        jsonBody.put("merchantRefNum", merchantRefNum);
        jsonBody.put("transactionType", "PAYMENT");
        jsonBody.put("paymentType", "PAYSAFECARD");
        jsonBody.put("amount", amount);
        jsonBody.put("currencyCode", "USD");
        jsonBody.put("customerIp", "172.0.0.1");

        JSONObject paysafecard = new JSONObject();
        paysafecard.put("consumerId", customerId);
        paysafecard.put("minAgeRestriction", 18);
        paysafecard.put("kycLevelRestriction", "SIMPLE");
        paysafecard.put("countryRestriction", "DE");

        jsonBody.put("paysafecard", paysafecard);

        JSONObject billingDetails = new JSONObject();
        billingDetails.put("nickName", "Home");
        billingDetails.put("street", "100 Queen");
        billingDetails.put("street2", "Unit 201");
        billingDetails.put("city", "Toronto");
        billingDetails.put("zip", "M5H 2N2");
        billingDetails.put("country", "CA");

        jsonBody.put("billingDetails", billingDetails);

        JSONObject merchantDescriptor = new JSONObject();
        merchantDescriptor.put("dynamicDescriptor", "OnlineStore");
        merchantDescriptor.put("phone", "12345678");

        jsonBody.put("merchantDescriptor", merchantDescriptor);

        JSONArray returnLinks = new JSONArray();

        JSONObject link1 = new JSONObject();
        link1.put("rel", "on_completed");
        link1.put("href", "https://usgaminggamblig.com/payment/return/success");
        link1.put("method", "GET");

        JSONObject link2 = new JSONObject();
        link2.put("rel", "on_failed");
        link2.put("href", "https://usgaminggamblig.com/payment/return/failed");
        link2.put("method", "GET");

        JSONObject link3 = new JSONObject();
        link3.put("rel", "default");
        link3.put("href", "https://usgaminggamblig.com/payment/");
        link3.put("method", "GET");

        returnLinks.put(link1);
        returnLinks.put(link2);
        returnLinks.put(link3);

        jsonBody.put("returnLinks", returnLinks);

        return jsonBody;
    }

    JSONObject PaymentRequestCreator( String paymentHandleToken)
    {
        JSONObject obj = new JSONObject();
        obj.put("merchantRefNum", merchantRefNum);
        obj.put("amount", amount);
        obj.put("currencyCode", "USD");
        obj.put("dupCheck", true);
        obj.put("settleWithAuth", true);
        obj.put("description", "Paysafe test transaction");
        obj.put("paymentHandleToken", paymentHandleToken);

        return obj;
    }

    String GenerateAuthHeader()
    {
        String auth = s_username + ":" + s_apiKey;
        String authHeader = "Basic " + Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));

        return authHeader;
    }

    String ReadPaymentHandleToken( Response response)
    {
        if (response.getStatus() == 200) {
            String jsonString = response.readEntity(String.class);
            JSONObject json = new JSONObject(jsonString);
            return json.getString("paymentHandleToken");
        } else {
            System.out.println("Failed : HTTP error code : " + response.getStatus());
        }
        return "failed";
    }

    void Send_CheckStatus( String authHeader)
    {
        Client client_check = ClientBuilder.newClient();
        Response response_check = client_check.target("https://api.test.paysafe.com/paymenthub/v1/monitor")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", authHeader)
                .get();

        String responseBody = response_check.readEntity(String.class);
        System.out.println("status: " + responseBody);
    }

    String Send_RequestPaymentHandle(String authHeader) {
        Client client = ClientBuilder.newClient();
        JSONObject body = PaymentHandleCreator(merchantRefNum, amount, "Customer1");

        Response response = client.target("https://api.test.paysafe.com/paymenthub/v1/paymenthandles")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", authHeader)
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .header("Simulator", "EXTERNAL")
                .post(Entity.json(body.toString()));

        String responseBody = response.readEntity(String.class);

        System.out.println("Response status: " + response.getStatus());
        System.out.println("Response body: " + responseBody);

        return ReadRedirectLink( responseBody, response.getStatus());
    }


    Response Send_RequestPayment( String authHeader, String paymentHandleToken)
    {
        JSONObject body = PaymentRequestCreator( paymentHandleToken);
        Client client = ClientBuilder.newClient();

        Response response = client.target("https://api.test.paysafe.com/paymenthub/v1/payments")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", authHeader)
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .header("Simulator", "EXTERNAL")
                .post(Entity.json( body));

        System.out.println("Response status: " + response.getStatus());
        System.out.println("Response body: " + response.readEntity(String.class));

        return response;
    }

    String ReadRedirectLink(String jsonString, int status) {
        if(status == 201) {
            JSONObject json = new JSONObject(jsonString);
            JSONArray links = json.getJSONArray("links");
            JSONObject firstLink = links.getJSONObject(0);
            return firstLink.getString("href");
        } else {
            System.out.println("Failed : HTTP error code : " + status);
        }
        return "failed";
    }

}
