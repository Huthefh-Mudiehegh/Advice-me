package com.huthfy.AdviceMe.data.dataSource.local.dataSource;

import android.util.Log;

import com.huthfy.AdviceMe.data.dataSource.local.dao.AdviceDao;
import com.huthfy.AdviceMe.data.model.Advice;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

public class LocalAdviceDataSource {
    AdviceDao adviceDao;

    private static final String MyTag = "MyTag";
    @Inject
    public LocalAdviceDataSource(AdviceDao adviceDao) {
        this.adviceDao = adviceDao;
    }


    public Observable<List<Advice>> getFavoriteAdvices(){
        return adviceDao.getFavoriteAdvices();
    }

   public Completable updateAdvice(Advice advice){
        return adviceDao.updateAdvice(advice);
   }

   public Observable<List<Advice>> getAdviceByFeel(String feelName){
       Log.d(MyTag, "getAdvices: LocalDataSource");

       return adviceDao.getAdvicesByFeel(feelName);
   }

    public Observable<List<Advice>> getAdvicesByFeelFilterTitle(String feelName,String adviceTitle){
        return adviceDao.getAdvicesByFeelFilterTitle(feelName,adviceTitle);
    }

}
