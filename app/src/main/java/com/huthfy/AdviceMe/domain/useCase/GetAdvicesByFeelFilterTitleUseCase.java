package com.huthfy.AdviceMe.domain.useCase;

import android.util.Log;

import com.huthfy.AdviceMe.data.model.Advice;
import com.huthfy.AdviceMe.data.repository.AdviceRepositoryImpl;
import com.huthfy.AdviceMe.domain.repositoryInterface.AdviceRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public class GetAdvicesByFeelFilterTitleUseCase {
    private static final String MyTag = "MyTag";
    public AdviceRepository adviceRepository;
    

    @Inject
    public GetAdvicesByFeelFilterTitleUseCase(AdviceRepositoryImpl adviceRepository) {
        this.adviceRepository = adviceRepository;
    }

    public Observable<List<Advice>> getAdviceByFeelFilterTitle(String feelName, String adviceTitle) {
        Log.d(MyTag, "getAdvices: useCase");

        return adviceRepository.getAdvicesByFeelFilterTitle(feelName,adviceTitle);
    }
}
