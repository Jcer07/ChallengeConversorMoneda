package conversor;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;

public class ApiConversorMoneda {
	private static String urlApi = "https://v6.exchangerate-api.com/v6/6c6aca309ae1078598ade7ba/pair/";

    public static double getConversion(String de, String a, double valor) throws URISyntaxException, IOException, InterruptedException{
        String url = urlApi + de + "/" + a + "/" + valor;
        
        HttpClient client = HttpClient.newHttpClient();
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .GET()
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        JSONObject objJson = new JSONObject(response.body());
        System.out.println(response);
        System.out.println(objJson);
        
        return objJson.getDouble("conversion_result");
    }
}
