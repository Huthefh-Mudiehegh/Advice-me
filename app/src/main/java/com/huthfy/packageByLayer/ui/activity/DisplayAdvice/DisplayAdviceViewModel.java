package com.huthfy.packageByLayer.ui.activity.DisplayAdvice;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.huthfy.packageByLayer.data.model.Advice;
import com.huthfy.packageByLayer.domain.useCase.GetAdvicesByFeelFilterTitleUseCase;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
public class DisplayAdviceViewModel extends ViewModel {
    private static final String TAG = "DisplayAdviceError";

    private MutableLiveData<DisplayAdviceUiState> displayAdviceUiStateMutableLiveData = new MutableLiveData<>();
    public LiveData<DisplayAdviceUiState> displayAdviceUiStateLiveData = displayAdviceUiStateMutableLiveData;
    private GetAdvicesByFeelFilterTitleUseCase getAdvicesByFeelFilterTitleUseCase;

    public DisplayAdviceViewModel() {
    }

    @Inject
    public DisplayAdviceViewModel(GetAdvicesByFeelFilterTitleUseCase getAdvicesByFeelFilterTitleUseCase) {
        this.getAdvicesByFeelFilterTitleUseCase = getAdvicesByFeelFilterTitleUseCase;
    }

    public void getAdvicesByFeelFilterTitle(String feelName,String adviceTitle){
        getAdvicesByFeelFilterTitleUseCase.getAdviceByFeelFilterTitle(feelName,adviceTitle)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new SingleObserver<List<Advice>>() {

            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull List<Advice> advice) {
                displayAdviceUiStateMutableLiveData.setValue(new DisplayAdviceUiState(advice));
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG, "onError: "+e.getMessage());
            }
        });

    }
}
