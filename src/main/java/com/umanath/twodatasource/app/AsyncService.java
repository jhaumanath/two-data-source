package com.umanath.twodatasource.app;

import com.umanath.twodatasource.annotation.ReadOnly;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class AsyncService {

    @Autowired
    private MyRepository myRepository;

    @Async
    @ReadOnly
    public CompletableFuture<List<MyEntity>> readData() {
        return CompletableFuture.completedFuture(myRepository.findAll());
    }

    @Async
    public void writeData(MyEntity entity) {
        myRepository.save(entity);
    }
}
