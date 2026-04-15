package com.example.citytime.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class TimeController {

    @GetMapping("/")
    public String home() {
        return "Şehir Saatleri API Aktif! Uygulama başarıyla CI/CD üzerinden Deploy edildi.<br>" +
                "Örnek Kullanım: <a href='/api/time?timezone=Europe/Istanbul'>/api/time?timezone=Europe/Istanbul</a> <br>" +
                "Örnek Kullanım: <a href='/api/time?timezone=Asia/Tokyo'>/api/time?timezone=Asia/Tokyo</a>";
    }

    @GetMapping("/api/time")
    public String getCityTime(@RequestParam(defaultValue = "Europe/Istanbul") String timezone) {
        try {
            ZoneId zoneId = ZoneId.of(timezone);
            ZonedDateTime now = ZonedDateTime.now(zoneId);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            return "Seçili Bölge (" + timezone + ") için yerel saat: <b>" + now.format(formatter) + "</b>";
        } catch (Exception e) {
            return "Hatalı saat dilimi formatı. Lütfen geçerli bir kıta/şehir giriniz. Örnek: Europe/London";
        }
    }
}
