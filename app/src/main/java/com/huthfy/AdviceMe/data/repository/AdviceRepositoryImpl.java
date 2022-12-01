package com.huthfy.AdviceMe.data.repository;

import android.util.Log;

import com.huthfy.AdviceMe.data.dataSource.local.dataSource.LocalAdviceDataSource;
import com.huthfy.AdviceMe.data.model.Advice;
import com.huthfy.AdviceMe.domain.repositoryInterface.AdviceRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public class AdviceRepositoryImpl implements AdviceRepository {
    private static final String MyTag = "MyTag";
    public LocalAdviceDataSource localAdviceDataSource;

    @Inject
    public AdviceRepositoryImpl(LocalAdviceDataSource localAdviceDataSource) {
        this.localAdviceDataSource = localAdviceDataSource;

    }

    @Override
    public Observable<List<Advice>> getAdvicesByFeel(String feelName) {
        Log.d(MyTag, "getAdvices: Repository");
        return localAdviceDataSource.getAdviceByFeel(feelName);
    }

    @Override
    public Observable<List<Advice>> getAdvicesByFeelFilterTitle(String feelName, String adviceTitle) {
        return localAdviceDataSource.getAdvicesByFeelFilterTitle(feelName,adviceTitle);
    }

    @Override
    public Observable<List<Advice>> getFavoriteAdvices() {
        return localAdviceDataSource.getFavoriteAdvices();
    }

    @Override
    public Completable updateAdvice(Advice advice) {
        return localAdviceDataSource.updateAdvice(advice);
    }


}
