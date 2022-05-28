package com.example.laboratory_work.counter;

import com.example.laboratory_work.counter.RequestCounter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestCounterController {
    @GetMapping("/counter")
    public String getCounter(){
        RequestCounter counter = new RequestCounter();
        RequestCounterThread thr = new RequestCounterThread();
        return "counter = " + counter.getCount();
    }
}