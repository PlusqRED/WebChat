package ru.grape.ws.service.impl;

import org.springframework.stereotype.Service;
import ru.grape.ws.service.DataLoader;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.function.Consumer;
import java.util.regex.Pattern;

@Service
public class WebDataLoader implements DataLoader {

    private final static Pattern jsPattern = Pattern.compile("^(?!.*\\.test\\.js$).*\\.js$");
    private final static Pattern cssPattern = Pattern.compile("^(?!.*\\.test\\.css$).*\\.js$");

    @Override
    public void load(String webUrl, Consumer<String> dataProcessor) {
        // Switch web parser logic related to HTTP or HTTPS
        if (webUrl.contains("https://")) {
            URL url;
            String data = "";
            try {
                url = new URL(webUrl);
                HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
                data = printContent(con);
            } catch (IOException e) {
                e.printStackTrace();
            }
            dataProcessor.accept(appendLeftUrl(webUrl, data));
        } else {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(webUrl))
                    .build();
            client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .thenApply(data -> appendLeftUrl(webUrl, data))
                    .thenAccept(dataProcessor)
                    .join();
        }

    }

    private String printContent(HttpsURLConnection con) {
        StringBuilder builder = new StringBuilder();
        if (con != null) {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String input;
                while ((input = br.readLine()) != null) {
                    builder.append(input);
                }
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return builder.toString();
    }

    private String appendLeftUrl(String url, String data) {
        data = data.replaceAll("images/", url + "images/");
        return data;
    }
}
