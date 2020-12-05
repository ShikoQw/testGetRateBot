package com.example.demo.botApi;


import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
@Slf4j
public class PingTask {
    private String url = "https://www.google.com";

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Scheduled(fixedRateString = "${pingtask.period}")
    public void pingMe(){
        System.out.println(url);
        try {
            URL url = new URL(getUrl());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            System.out.println("Подключение к www.google.com прошло успешно");
            connection.disconnect();
        }catch (IOException e ){
            e.printStackTrace();
        }
    }
}
