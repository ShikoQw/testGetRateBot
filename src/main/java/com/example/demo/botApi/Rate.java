package com.example.demo.botApi;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class Rate {
    public String getRateToUser(String choose){
        //парсинг сайта
        try {
            Document document = Jsoup.connect("https://kurs-tenge.kz/almaty/halykbank-almaty/")
                    .userAgent("Chrome/4.0.249.0 Safari/532.5")
                    .referrer("http://www.google.com")
                    .get();
            Elements table = document.getElementsByClass("kyrsvalyt");
        //вытаскиваем нужные строки
            String getCourseData = table.text();
            String[] words = getCourseData.split("\\s");
            ArrayList<String> rateArray = new ArrayList<>();
            for (int i = 0; i < words.length; i++) {
                if (words[i].equals(choose)) {
                    for (int j = 0; j < 2; j++) {
                        rateArray.add(words[i + 1]);
                        i++;
                    }
                }
            }
            if(!rateArray.isEmpty()) {
                DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                Date date = new Date();
                String dateString = dateFormat.format(date);
                String finalToUserString = dateString + "\n\nдля покупки составляет: " + rateArray.get(0) + "\nдля продажи: " + rateArray.get(1);
                return finalToUserString;
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    return null;
    }
}