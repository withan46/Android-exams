package gr.uom.weatherapiactivity;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.*;


public class FetchWeatherTask implements Runnable{
    String result;
    String location = "Thessaloniki,Greece";


    public FetchWeatherTask(String location) {
        System.out.println("Location:"+location);
        if (location.length()>0)
            this.location = location;
    }

    private List<String> getWeatherDataFromJson(String jsonStr)  throws JSONException {
        List<String> resultStrings = new ArrayList<>();

        JSONObject json = new JSONObject(jsonStr);
        String temp = json.getJSONObject("main").getString("temp").toString();
        String minTemp = json.getJSONObject("main").getString("temp_min").toString();
        String maxTemp = json.getJSONObject("main").getString("temp_max").toString();
        resultStrings.add("Temperature:" + temp + "\nForecast for Today: " + minTemp + " to " + maxTemp);
        return resultStrings;

    }

    @Override
    public void run() {
        String url= "https://api.openweathermap.org/data/2.5/weather?q="+location+"&mode=json&units=metric&APPID=27949ea6b6dffa1dad1deb925c9b024b";

        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create("", MediaType.parse("text/plain"));
        Request request = new Request.Builder().url(url).method("POST", body).build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            String data = response.body().string();
            System.out.println("My Response: " + data);
            try {
                result = getWeatherDataFromJson(data).get(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String getResult() {
            return result;
    }
}

