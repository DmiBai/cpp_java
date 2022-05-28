package com.example.laboratory_work;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.laboratory_work.counter.RequestCounterThread;
import com.example.laboratory_work.exception.DataRequestException;
import com.example.laboratory_work.exception.DataRequestException1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.ServletRequest;
import java.util.ArrayList;
import java.util.List;


@RestController
public class Controller {
    private RequestCounterThread counterThread;
    private Logger logger = LoggerFactory.getLogger(Controller.class);
    private CalculationService newService;

    @Autowired
    public void setter(CalculationService newService){
        this.newService = newService;
    }

    private static final String template = "The words \" %s \" have %d symbols \"%c\"";
    private static final String template1 = "The words %s have %d symbols %c\n";

    @GetMapping("/triangle")
    public String triangleReturn(@RequestParam(value = "first", defaultValue = "1") long first,
                                 @RequestParam(value = "second", defaultValue = "1") long second,
                                 @RequestParam(value = "third", defaultValue = "1") long third)
            throws DataRequestException, DataRequestException1 {
        counterThread = new RequestCounterThread();

        if ((first <= 0) || (second <= 0) || (third <= 0)) {
            throw new DataRequestException("Negative numbers and zero are not allowed. They can not be triangle sites values.");
        }

        if ((first >= second + third) || (second >= third + first) || (third >= second + first)) {
            throw new DataRequestException1("This triangle does not exist. Or it is not triangle, but the line :)");
        }

        return new DataClass(first, second, third).getResult();
    }

    
    @GetMapping("/triangleasync")
    public String triangleReturnAsync(@RequestParam(value = "first", defaultValue = "1") long first,
                                 @RequestParam(value = "second", defaultValue = "1") long second,
                                 @RequestParam(value = "third", defaultValue = "1") long third)
                                  throws DataRequestException, DataRequestException1 {

        if ((first <= 0) || (second <= 0) || (third <= 0)) {
            throw new DataRequestException("Negative numbers and zero are not allowed. They can not be triangle sites values.");
        }

        if ((first >= second + third) || (second >= third + first) || (third >= second + first)) {
            throw new DataRequestException1("This triangle does not exist. Or it is not triangle, but the line :)");
        }
        
        counterThread = new RequestCounterThread();
        //new Long(newService.repository.findAll().size()),
        DataClass gr = new DataClass( first, second, third);
        return String.valueOf(newService.calcAsync(gr));
    }

    @PostMapping("/Data")
    public String post(@RequestBody List<DataClass> list) {
        List<Integer> res = new ArrayList<>();
        List<String> res_output = new ArrayList<>();
        list.forEach((element) -> {
            try {
                element.setResult();
                res_output.add(element.getResult());
                res.add(newService.calc(element));
            } catch (Throwable e) {
                logger.info("SMTH WENT WRONG");
            }
        });

        long sizeOfRequest = newService.calcSize(res);
        long often = newService.mostRecurring(res);
        return (res_output + "\nSize = " + sizeOfRequest + "\nOfften = " + often);
    }

}
