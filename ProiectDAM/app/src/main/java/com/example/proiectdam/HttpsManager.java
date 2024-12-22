package com.example.proiectdam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class HttpsManager {

    private String urlAdress;
    private BufferedReader reader;
    private HttpsURLConnection connection;

    public HttpsManager(String urlAdress) {
        this.urlAdress = urlAdress;
    }

    public String procesare(){
        try{
            return preluareJsonHttps();
        }catch (IOException e){
            throw new RuntimeException(e);
        }finally {
            inchidere();
        }
    }
    public void inchidere(){
        try{
            reader.close();
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
        connection.disconnect();
    }
    private String preluareJsonHttps() throws IOException {
        connection = (HttpsURLConnection) new URL(urlAdress).openConnection();
        reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder stb = new StringBuilder();
        String line;
        while((line = reader.readLine())!=null){
            stb.append(line);
        }
        return stb.toString();
    }
}
