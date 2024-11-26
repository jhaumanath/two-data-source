package com.umanath.twodatasource.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MyService {

    private final MyRepository myRepository;

    @Autowired
    public MyService(MyRepository myRepository) {
        this.myRepository = myRepository;
    }

    @Transactional(readOnly = true)
    public List<MyEntity> readData() {
        return myRepository.findAll();
    }

    @Transactional
    public void writeData(MyEntity entity) {
        myRepository.save(entity);
    }
}
