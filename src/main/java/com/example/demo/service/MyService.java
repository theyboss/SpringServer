package com.example.demo.service;

import com.example.demo.DemoApplication;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Point;
import org.influxdb.dto.Pong;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

@Service
public class MyService {

    InfluxDB influxDB;

    Logger logger = LoggerFactory.getLogger(MyService.class);

    private ConcurrentMap<String, Integer> map = new ConcurrentHashMap<>();

    public Object doSomething(String key) {
        if (map.containsKey(key)) {
            map.put(key, map.get(key) + 1);
        } else {
            map.put(key, 1);
        }

        try {
            throw new Exception("Bla bla");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return e.getStackTrace();
        }
//        return "";
    }

    @Scheduled(fixedRate = 20000)
    public void doSomethingWithMap() {
        Map<String, Integer> mapToInflux = this.map;
        map.clear();
        System.out.println(mapToInflux);


        //for (String key : mapToInflux.keySet()) {
            Point point = Point.measurement("memory")
                    .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                    .addField("name", "server1")
                    .addField("free", 4743656L)
                    .addField("used", 1015096L)
                    .addField("buffer", 1010467L)
                    .build();
            this.influxDB.write(point);
       // }
        influxDB.close();
    }
//    @Scheduled(initialDelay = 1000)
//        public void startInfluxDB() {
//
//            influxDB = InfluxDBFactory.connect("http://127.0.0.1:8086", "root", "root");
//
//            influxDB.enableBatch(100, 200, TimeUnit.MILLISECONDS);
//            influxDB.setRetentionPolicy("defaultPolicy");
//            influxDB.setDatabase("TEST");
//            Pong response = influxDB.ping();
//            if (response.getVersion().equalsIgnoreCase("unknown")) {
//                try {
//                    throw new Exception("Error pinging server.");
//                } catch (Exception e) {
//                    System.out.println(e.getStackTrace());
//                }
//            }
//    }
}
