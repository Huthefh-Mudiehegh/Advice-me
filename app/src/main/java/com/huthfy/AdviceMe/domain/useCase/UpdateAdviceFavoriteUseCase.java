package com.huthfy.AdviceMe.domain.useCase;

import com.huthfy.AdviceMe.data.repository.AdviceRepositoryImpl;
import com.huthfy.AdviceMe.data.model.Advice;
import com.huthfy.AdviceMe.domain.repositoryInterface.AdviceRepository;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Completable;


public class UpdateAdviceFavoriteUseCase {
    public AdviceRepository adviceRepository;

    @Inject
    public UpdateAdviceFavoriteUseCase(AdviceRepositoryImpl adviceRepository) {
        this.adviceRepository = adviceRepository;
    }

    public Completable update(Advice advice) {
        return adviceRepository.updateAdvice(advice);
    }
    

}
