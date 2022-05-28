package com.example.laboratory_work;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CalculationService {
    private CalculationCache hashMap;
    private Logger logger = LoggerFactory.getLogger(CalculationService.class);

    @Autowired
    public void setHashMap(CalculationCache hashMap) {
        this.hashMap = hashMap;
    }

    @Autowired
    public Repository repository;

    public int calc(DataClass gr) {
        int count = 0;
        if (hashMap.isContain(gr)) {
            count = hashMap.getParam(gr);
            logger.info("Get hashMap");
        } else {
            count += String.valueOf(gr.getFirst()).length();
            count += String.valueOf(gr.getSecond()).length();
            count += String.valueOf(gr.getThird()).length();
            hashMap.addToMap(gr, count);
            //repository.save(gr);
            logger.info("Add to hashMap");
        }
        return count;
    }

    public long calcAsync(DataClass gr) {
        int count = 0;
        Long localId;
            count += String.valueOf(gr.getFirst()).length();
            count += String.valueOf(gr.getSecond()).length();
            count += String.valueOf(gr.getThird()).length();
           Thread thread=new Thread();
           thread.start();

           Runnable runnable=new Runnable() {
               @Override
               public void run() {

               }
           };
           repository.save(gr);
           logger.info("Add to hashMap");

        return (count);
    }

    public long calcSize(List<Integer> resList) {
        long size = 0;
        if (!resList.isEmpty()) {
            size = resList.stream().count();
        }
        return size;
    }

    public int mostRecurring(List<Integer> resList) {
        int size = 0;
        int max = 0;
        int numb = 0;
        HashSet<Integer> res = new HashSet<>();
        res.addAll(resList);
        for (int a : res) {
            size = Collections.frequency(resList, a);
            if (size >= max) {
                numb = a;
                max = size;
            }
        }
        return numb;
    }
}
