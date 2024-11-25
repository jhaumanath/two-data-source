package com.umanath.twodatasource.app;

import com.umanath.twodatasource.config.RoutingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyService {

    private final MyRepository myRepository;

    @Autowired
    public MyService(MyRepository myRepository) {
        this.myRepository = myRepository;
    }

    public List<MyEntity> readData() {
        System.out.println("Setting DataSource to read before transaction starts");
        RoutingContext.setCurrentDb("read");
        List<MyEntity> data;
        try {
            data = myRepository.findAll();
        } finally {
            RoutingContext.clear();
        }
        return data;
    }

    public void writeData(MyEntity entity) {
        System.out.println("Setting DataSource to write before transaction starts");
        RoutingContext.setCurrentDb("write");
        try {
            myRepository.save(entity);
        } finally {
            RoutingContext.clear();
        }
    }
}
