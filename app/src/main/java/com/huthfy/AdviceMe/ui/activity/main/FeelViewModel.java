package com.huthfy.AdviceMe.ui.activity.main;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.huthfy.AdviceMe.data.model.Feel;
import com.huthfy.AdviceMe.domain.useCase.GetAllFeelsUseCase;

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
                .subscribe(feels->feelUiStateMutableLiveData.setValue(new FeelUiState(feels)));
    }

}
