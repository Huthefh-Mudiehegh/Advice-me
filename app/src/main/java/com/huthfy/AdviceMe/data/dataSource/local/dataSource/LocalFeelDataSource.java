package com.huthfy.AdviceMe.data.dataSource.local.dataSource;

import com.huthfy.AdviceMe.data.dataSource.local.dao.FeelDao;
import com.huthfy.AdviceMe.data.model.Feel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class LocalFeelDataSource {
    FeelDao feelDao;
    @Inject
    public LocalFeelDataSource(FeelDao feelDao) {
        this.feelDao = feelDao;
    }


    public Single<List<Feel>> getAllFeels(){
        return feelDao.getAllFeels();
    }
}
