package hard.tests;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.config.ConnectionConfig;
import org.apache.hc.client5.http.impl.DefaultConnectionKeepAliveStrategy;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.core5.util.Timeout;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static java.net.http.HttpResponse.BodyHandlers;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.HttpEntity.EMPTY;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpStatus.OK;

public class HttpTests {

    private static final String requestUrl = "https://postman-echo.com/get";

    // Lower level HTTP API (legacy JDK)
    @Test
    public void basicHttpClient() throws IOException {
        URL url = URI.create(requestUrl).toURL();
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            String inputLine;
            StringBuilder responseBody = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                responseBody.append(inputLine);
            }
            System.out.println(responseBody);
            assertEquals(OK.value(), conn.getResponseCode());
            JsonNode json = getJson(responseBody.toString());
            assertHttpResponse(json);
        }
    }

    // Java 11 HTTP API
    @Test
    public void java11HttpClientTest() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(requestUrl))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

        System.out.println(response.body());
        assertEquals(OK.value(), response.statusCode());
        assertNotNull(response.body());
        JsonNode json = getJson(response.body());
        assertHttpResponse(json);
    }

    // Spring Abstraction over HTTP client
    @Test
    public void restTemplateTest() throws IOException {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.exchange(requestUrl, GET, EMPTY, String.class);

        System.out.println(response.getBody());
        assertEquals(OK, response.getStatusCode());
        assertNotNull(response.getBody());
        JsonNode json = getJson(response.getBody());
        assertHttpResponse(json);
    }

    // Production configuration RestTemplate + pooled HttpClient
    @Test
    public void keepAliveHttpClientTest() throws IOException {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(100);
        connectionManager.setDefaultMaxPerRoute(20);
        connectionManager.setConnectionConfigResolver(
                o -> ConnectionConfig.custom()
                        .setConnectTimeout(Timeout.ofMilliseconds(5000))
                        .build());

        try (CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(connectionManager)
                .setKeepAliveStrategy(DefaultConnectionKeepAliveStrategy.INSTANCE)
                .build()) {
            ClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
            RestTemplate restTemplate = new RestTemplate(factory);
            ResponseEntity<String> response = restTemplate.exchange(requestUrl, GET, EMPTY, String.class);

            System.out.println(response.getBody());
            assertEquals(OK, response.getStatusCode());
            JsonNode json = getJson(response.getBody());
            assertHttpResponse(json);
        }
    }

    private JsonNode getJson(String responseBody) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(responseBody);
    }

    private void assertHttpResponse(JsonNode json) {
        assertEquals(requestUrl, json.get("url").asText());
        assertTrue(json.has("args"));
        assertTrue(json.has("headers"));
    }
}
