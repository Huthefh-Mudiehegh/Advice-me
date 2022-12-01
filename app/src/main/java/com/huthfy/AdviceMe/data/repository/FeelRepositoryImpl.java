package com.huthfy.AdviceMe.data.repository;

import com.huthfy.AdviceMe.data.dataSource.local.dataSource.LocalFeelDataSource;
import com.huthfy.AdviceMe.data.model.Feel;
import com.huthfy.AdviceMe.domain.repositoryInterface.FeelRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class FeelRepositoryImpl implements FeelRepository {
    LocalFeelDataSource localFeelDataSource;

    @Inject
    public FeelRepositoryImpl(LocalFeelDataSource localFeelDataSource) {
        this.localFeelDataSource = localFeelDataSource;
    }

    @Override
    public Single<List<Feel>> getAllFeels() {
        return localFeelDataSource.getAllFeels();
    }
}
