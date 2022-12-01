package com.huthfy.AdviceMe.domain.repositoryInterface;

import com.huthfy.AdviceMe.data.model.Feel;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface FeelRepository {
    public Single<List<Feel>> getAllFeels();
}
