package com.example.citytime.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TimeController {

    // Artık ana sayfa görevini static/index.html yapacak, bu yüzden "/" metodunu kaldırdık

    @GetMapping("/api/time")
    public Map<String, String> getCityTime(@RequestParam(defaultValue = "Europe/Istanbul") String timezone) {
        Map<String, String> response = new HashMap<>();
        try {
            ZoneId zoneId = ZoneId.of(timezone);
            ZonedDateTime now = ZonedDateTime.now(zoneId);
            
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy, EEEE");
            
            response.put("status", "success");
            response.put("time", now.format(timeFormatter));
            response.put("date", now.format(dateFormatter));
            response.put("region", timezone);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Hatalı saat dilimi formatı!");
        }
        return response;
    }
}
