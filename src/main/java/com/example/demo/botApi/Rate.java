package com.example.demo.botApi;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;


public class Rate {
    public String getRateToUser(String choose){
        //Парсинг сайта
        try {
            Document document = Jsoup.connect("https://bai.kz/bank/narodnyi-bank/kursy/")
                    .userAgent("Chrome/4.0.249.0 Safari/532.5")
                    .referrer("http://www.google.com")
                    .get();
            Elements table = document.getElementsByTag("tbody");
        //Вытаскиваем нужные строки
            String getCourseData = table.text();
            String[] words = getCourseData.split("\\s");
            ArrayList<String> rateArray = new ArrayList<>();
            for (int i = 0; i < words.length; i++) {
                if (words[i].equals(choose)) {
                    for (int j = 0; j < 3; j++) {
                        rateArray.add(words[i+1]);
                        i++;
                    }
                }
            }
            String finalToUserString = rateArray.get(2) + "\n\nдля покупки наличными составляет: "+rateArray.get(0)+" для продажи: "+rateArray.get(1) +
                    "\nдля покупки безналичными составляет "+rateArray.get(3)+" для продажи: "+rateArray.get(4) ;
            return finalToUserString;
        } catch (IOException e){
            e.printStackTrace();
        }
    return null;
    }
}