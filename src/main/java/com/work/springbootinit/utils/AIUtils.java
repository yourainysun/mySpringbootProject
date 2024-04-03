package com.work.springbootinit.utils;//import java.net.URI;
//import java.net.http.HttpClient;
//import java.net.http.HttpHeaders;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
//import java.util.concurrent.CompletableFuture;
//
//public class OpenAIChatExample {
//
//    private static String API_KEY = "your-api-key";
//    private static final String API_URL = "https://api.openai.com/v1/chat/completions";
//
//    public static void main(String[] args) throws Exception {
//        HttpClient client = HttpClient.newHttpClient();
//
//        String requestBody = "{"
//                + "\"model\": \"gpt-3.5-turbo\","
//                + "\"messages\": ["
//                + "{ \"role\": \"system\", \"content\": \"You are a helpful assistant.\" },"
//                + "{ \"role\": \"user\", \"content\": \"Hello!\" }"
//                + "]"
//                + "}";
//
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(new URI(API_URL))
//                .header("Content-Type", "application/json")
//                .header("Authorization", "Bearer " + API_KEY)
//                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
//                .build();
//
//        CompletableFuture<HttpResponse<String>> response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
//
//        response.thenAccept(r -> {
//            System.out.println("Response code: " + r.statusCode());
//            HttpHeaders headers = r.headers();
//            headers.map().forEach((k, v) -> System.out.println(k + ":" + v));
//            System.out.println("Response body: " + r.body());
//        });
//
//        // Wait for the response
//        response.join();
//    }
//}
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class AIUtils {
    private static String API_KEY = "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";
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";

    public static void main(String[] args) throws Exception {
        URL url = new URL(API_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Authorization", "Bearer " + API_KEY);
        conn.setDoOutput(true);

        String requestBody = "{"
                + "\"model\": \"gpt-3.5-turbo\","
                + "\"messages\": ["
                + "{ \"role\": \"system\", \"content\": \"You are a helpful assistant.\" },"
                + "{ \"role\": \"user\", \"content\": \"Hello!\" }"
                + "]"
                + "}";

        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = requestBody.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response.toString());
        }
    }
}