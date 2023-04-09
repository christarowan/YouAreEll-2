package controllers;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Id;

public class IdController {
    private HashMap<String, Id> allIds;

    Id myId;

    public ArrayList<Id> getIds() {
        try {
            HttpRequest request = (HttpRequest) HttpRequest.newBuilder()
                    .uri(new URI("http://zipcode.rocks:8085/ids"))
                    .GET()
                    .build();
            HttpResponse<String> response = HttpClient
                    .newBuilder()
                    .build()
                    .send(request, HttpResponse.BodyHandlers.ofString());
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
            String body = response.body();

            // Read the contents of an entity and return it as a String.
            List<Id> listId = objectMapper.readValue(body, new TypeReference<List<Id>>(){});
            return (ArrayList<Id>) listId;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public Id postId(Id id) {

        // create json from id
        // call server, get json result Or error
        // result json to Id obj

        return null;
    }

    public Id putId(Id id) {



        return null;
    }

    public static void main(String[] args) {
        try {
            HttpRequest request = (HttpRequest) HttpRequest.newBuilder()
                    .uri(new URI("http://zipcode.rocks:8085/ids"))
                    .GET()
                    .build();
            HttpResponse<String> response = HttpClient
                    .newBuilder()
                    .build()
                    .send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
            String body = response.body();

            // Read the contents of an entity and return it as a String.
            System.out.println(body);
            List<Id> listId = objectMapper.readValue(body, new TypeReference<List<Id>>(){});
            System.out.println("About to print list size...");
            System.out.println(listId.size());
            for(int i = 0; i < listId.size(); i++) {
                System.out.println(listId.get(i).toString() + " ");
            }
        } catch (Exception e) {
            System.out.println("Oops error!"+e);
        }

   try {
       String requestBody = "{\n" +
               "   \"sequence\": \"-\",\n" +
               "   \"timestamp\": \" \",\n" +
               "   \"fromid\": \"ChristaR\",\n" +
               "   \"toid\": \"ule270\",\n" +
               "   \"message\": \"Hello it's me\"\n" +
               "}";
       HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://zipcode.rocks:8085/ids/ChristaR/messages"))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
       HttpResponse<String> response = HttpClient
               .newBuilder()
               .build()
               .send(request, HttpResponse.BodyHandlers.ofString());
       System.out.println(response.body());
        } catch (Exception e) {
                System.out.println("Oops error!"+e);
            }
    }


}