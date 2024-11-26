package com.umanath.twodatasource.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

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
        System.out.println("AOP is invoked------------");
        return myRepository.findAll();
    }

    @Transactional
    public void writeData(MyEntity entity) {
        /*System.out.println("Write Transaction");
        System.out.println(TransactionSynchronizationManager.isActualTransactionActive());
        System.out.println(TransactionSynchronizationManager.isCurrentTransactionReadOnly());*/
        myRepository.save(entity);
    }
}
