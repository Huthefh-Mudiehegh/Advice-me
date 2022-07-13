package com.huthfy.packageByLayer.ui.activity.main;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.huthfy.packageByLayer.data.model.Feel;
import com.huthfy.packageByLayer.domain.useCase.GetAllFeelsUseCase;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
public class FeelViewModel extends ViewModel {
    private static final String MyTag = "MyTag";


    private MutableLiveData<FeelUiState> feelUiStateMutableLiveData = new MutableLiveData<>();
    public LiveData<FeelUiState> feelUiStateLiveData = feelUiStateMutableLiveData;

    private GetAllFeelsUseCase getAllFeelsUseCase;

    public FeelViewModel() {
    }

    @Inject
    public FeelViewModel(GetAllFeelsUseCase getAllFeelsUseCase) {
        this.getAllFeelsUseCase = getAllFeelsUseCase;
    }

    public void getAllFeels() {
        getAllFeelsUseCase.getAllFeels().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Feel>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull List<Feel> feels) {
                        feelUiStateMutableLiveData.setValue(new FeelUiState(feels));
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(MyTag, "onError: get feels error" + e.getMessage());
                    }
                });
    }

}
