package com.umanath.twodatasource.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class MyController {

    private final MyService myService;

    @Autowired
    public MyController(MyService myService) {
        this.myService = myService;
    }

    @GetMapping("/read")
    public List<MyEntity> readData() throws ExecutionException, InterruptedException {
        return myService.readData();
    }

    @PostMapping("/write")
    public void writeData(@RequestBody MyEntity entity) {
        myService.writeData(entity);
    }
}
