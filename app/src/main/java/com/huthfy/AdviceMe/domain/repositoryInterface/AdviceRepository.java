package com.huthfy.AdviceMe.domain.repositoryInterface;

import com.huthfy.AdviceMe.data.model.Advice;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public interface AdviceRepository {
    Observable<List<Advice>> getAdvicesByFeel(String feelName);

    Observable<List<Advice>> getAdvicesByFeelFilterTitle(String feelName,String adviceTitle);

    Observable<List<Advice>> getFavoriteAdvices();

    Completable updateAdvice(Advice advice);
}
