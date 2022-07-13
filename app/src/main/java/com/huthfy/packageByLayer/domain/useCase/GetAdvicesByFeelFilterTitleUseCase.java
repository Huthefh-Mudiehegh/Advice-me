package com.huthfy.packageByLayer.domain.useCase;

import android.util.Log;

import com.huthfy.packageByLayer.data.model.Advice;
import com.huthfy.packageByLayer.data.repository.AdviceRepositoryImpl;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class GetAdvicesByFeelFilterTitleUseCase {
    private static final String MyTag = "MyTag";
    public AdviceRepositoryImpl adviceRepository;

    //TODO: should inject [AdviceRepositoryImpl] or [AdviceRepository]
        // if [AdviceRepository] how can I do it?

    @Inject
    public GetAdvicesByFeelFilterTitleUseCase(AdviceRepositoryImpl adviceRepository) {
        this.adviceRepository = adviceRepository;
    }

    public Single<List<Advice>> getAdviceByFeelFilterTitle(String feelName,String adviceTitle) {
        Log.d(MyTag, "getAdvices: useCase");

        return adviceRepository.getAdvicesByFeelFilterTitle(feelName,adviceTitle);
    }
}
