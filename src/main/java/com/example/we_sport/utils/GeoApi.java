package com.example.we_sport.utils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import java.util.Map;
public class GeoApi {

    public GeoApi () {
    }
    public static String getInfosFromCity() {
        String response ="";
        try {
            URL url = new URL("https://nominatim.openstreetmap.org/search?q=Tunis+Bardo&format=json&polygon=1&addressdetails=1");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            int responseCode = con.getResponseCode();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response2 = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response2.append(inputLine);
            }
            in.close();
            System.out.println(response2.toString());
            JSONObject myResponse = new JSONObject(response.toString());




        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(response);
        return response;
    }


    public String getLong(String add) {
//        HttpResponse<JsonNode> apiResponse = Unirest.get("https://dog.ceo/api/breeds/image/random").asJson();
        return "";
    }
    public String getLat (String Add) {
    return "";
    }
}
