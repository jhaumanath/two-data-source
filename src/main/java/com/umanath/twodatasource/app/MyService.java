package com.umanath.twodatasource.app;

import com.umanath.twodatasource.annotation.ReadOnly;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class MyService {

    @Autowired
    private  AsyncService asyncService;

    public List<MyEntity> readData() throws ExecutionException, InterruptedException {
        return asyncService.readData().get();
    }

    @Transactional
    public void writeData(MyEntity entity) {
        asyncService.writeData(entity);
    }
}
