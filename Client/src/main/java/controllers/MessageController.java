package controllers;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Id;
import models.Message;

public class MessageController {

    private HashSet<Message> messagesSeen;
    // why a HashSet??

    public ArrayList<Id> getMessages() {
        try {
            HttpRequest request = (HttpRequest) HttpRequest.newBuilder()
                    .uri(new URI("http://zipcode.rocks:8085/messages"))
                    .GET()
                    .build();
            HttpResponse<String> response = HttpClient
                    .newBuilder()
                    .build()
                    .send(request, HttpResponse.BodyHandlers.ofString());
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            String body = response.body();

            // Read the contents of an entity and return it as a String.
            List<Id> listId = objectMapper.readValue(body, new TypeReference<List<Id>>() {
            });
            return (ArrayList<Id>) listId;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public Message postMessage(String myId, String toId, String msg) {
        try {
            ObjectMapper maps = new ObjectMapper();
            Message mess = new Message(msg, myId, toId);
            String message = maps.writeValueAsString(mess);
            HttpRequest request1 = (HttpRequest) HttpRequest.newBuilder()
                    .uri(new URI("http://zipcode.rocks:8085/ids/" + toId + "/messages"))
                    .POST(HttpRequest.BodyPublishers.ofString(message))
                    .build();
            HttpResponse<String> response1 = HttpClient
                    .newBuilder()
                    .build()
                    .send(request1, HttpResponse.BodyHandlers.ofString());
            System.out.println(response1.body());
            return mess;
        } catch (Exception e) {
            System.out.printf("error" + e);
        }
        return null;
    }

    public ArrayList<Message> getMessagesForId(String id) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            HttpRequest request = (HttpRequest) HttpRequest.newBuilder()
                    .uri(new URI("http://zipcode.rocks:8085/ids/" + id + "/messages"))
                    .GET()
                    .build();
            HttpResponse<String> response = HttpClient
                    .newBuilder()
                    .build()
                    .send(request, HttpResponse.BodyHandlers.ofString());

            // Read the contents of an entity and return it as a String.
            String body = response.body();
            List<Message> messageList = objectMapper.readValue(body, new TypeReference<ArrayList<Message>>() {});
            return (ArrayList<Message>) messageList;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }


        public Message getMessageForSequence (String id, String seq){
            try {
                HttpRequest request = (HttpRequest) HttpRequest.newBuilder()
                        .uri(new URI("http://zipcode.rocks:8085/ids/" + id + "/messages/" + seq))
                        .GET()
                        .build();
                HttpResponse<String> response = HttpClient
                        .newBuilder()
                        .build()
                        .send(request, HttpResponse.BodyHandlers.ofString());
                //            System.out.println(response);
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

                String body = response.body();
                //            System.out.println(body);
                Message newM = new Message();
                newM = objectMapper.readValue(body, new TypeReference<Message>() {
                });
                return newM;
            } catch (Exception e) {
                System.out.println("error" + e);
            }
            return null;
        }

    public ArrayList<Message> getMessagesFromFriend(Id myId, Id friendId) {
        return null;
    }

  //  public Message postMessage(Id myId, Id toId, Message msg) {
  //      return null;
  //  }

    public static void main(String[] args) {
        MessageController mc = new MessageController();
        ArrayList<Message> result = mc.getMessagesForId("ChristaR");

    }
 
}