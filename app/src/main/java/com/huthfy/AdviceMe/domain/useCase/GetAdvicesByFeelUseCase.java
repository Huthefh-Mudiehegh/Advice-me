package com.huthfy.AdviceMe.domain.useCase;

import android.util.Log;

import com.huthfy.AdviceMe.data.model.Advice;
import com.huthfy.AdviceMe.data.repository.AdviceRepositoryImpl;
import com.huthfy.AdviceMe.domain.repositoryInterface.AdviceRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public class GetAdvicesByFeelUseCase {
    private static final String MyTag = "MyTag";
    public AdviceRepository adviceRepository;

    //TODO: should inject [AdviceRepositoryImpl] or [AdviceRepository]
        // if [AdviceRepository] how can I do it?

    @Inject
    public GetAdvicesByFeelUseCase(AdviceRepositoryImpl adviceRepository) {
        this.adviceRepository = adviceRepository;
    }

    public Observable<List<Advice>> getAdviceByFeel(String feelName) {
        Log.d(MyTag, "getAdvices: useCase");

        return adviceRepository.getAdvicesByFeel(feelName);
    }
}
