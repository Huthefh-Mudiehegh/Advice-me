package com.huthfy.AdviceMe.domain.useCase;

import com.huthfy.AdviceMe.data.model.Advice;
import com.huthfy.AdviceMe.data.repository.AdviceRepositoryImpl;
import com.huthfy.AdviceMe.domain.repositoryInterface.AdviceRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public class GetFavoriteAdvicesUseCase {

    public AdviceRepository adviceRepository;

    @Inject
    public GetFavoriteAdvicesUseCase(AdviceRepositoryImpl adviceRepository) {
        this.adviceRepository = adviceRepository;
    }

    public Observable<List<Advice>> getFavoriteAdvices() {

        return adviceRepository.getFavoriteAdvices();
    }
}
