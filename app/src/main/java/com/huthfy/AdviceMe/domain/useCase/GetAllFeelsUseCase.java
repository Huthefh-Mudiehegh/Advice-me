package com.huthfy.AdviceMe.domain.useCase;

import com.huthfy.AdviceMe.data.model.Feel;
import com.huthfy.AdviceMe.data.repository.FeelRepositoryImpl;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class GetAllFeelsUseCase {
    FeelRepositoryImpl feelRepository;

    @Inject
    public GetAllFeelsUseCase(FeelRepositoryImpl feelRepository) {
        this.feelRepository = feelRepository;
    }

    public Single<List<Feel>> getAllFeels(){
        return feelRepository.getAllFeels();
    }
}
