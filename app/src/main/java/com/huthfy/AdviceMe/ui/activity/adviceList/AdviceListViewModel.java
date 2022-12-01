package com.huthfy.AdviceMe.ui.activity.adviceList;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.huthfy.AdviceMe.data.model.Advice;
import com.huthfy.AdviceMe.domain.useCase.GetAdvicesByFeelUseCase;
import com.huthfy.AdviceMe.domain.useCase.UpdateAdviceFavoriteUseCase;


import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;

import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
public class AdviceListViewModel extends ViewModel {
    private static final String MyTag = "MyTag";

    private final MutableLiveData<AdviceListUiState> adviceUiStateMutableLiveData = new MutableLiveData<>();
    public LiveData<AdviceListUiState> adviceUiStateLiveData = adviceUiStateMutableLiveData;


    private GetAdvicesByFeelUseCase getAdvicesByFeelUseCase;

    private UpdateAdviceFavoriteUseCase updateAdviceFavoriteUseCase;

    public AdviceListViewModel() {

    }

    @Inject
    public AdviceListViewModel(GetAdvicesByFeelUseCase getAdvicesByFeelUseCase,
                               UpdateAdviceFavoriteUseCase updateAdviceFavoriteUseCase) {
        this.getAdvicesByFeelUseCase = getAdvicesByFeelUseCase;
        this.updateAdviceFavoriteUseCase = updateAdviceFavoriteUseCase;

    }

    public void getAdvicesByFeel(String feelName) {
        Log.d(MyTag, "getAdvices: viewModel");
        getAdvicesByFeelUseCase.getAdviceByFeel(feelName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(advices -> adviceUiStateMutableLiveData.setValue(new AdviceListUiState(advices)),
                        error -> adviceUiStateMutableLiveData.setValue(new AdviceListUiState(error.getMessage()))
                );
    }


    public void updateAdviceFavoriteStatus(Advice advice) {
        updateAdviceFavoriteUseCase.update(advice)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> adviceUiStateMutableLiveData.getValue().setUpdated(true),
                        error -> adviceUiStateMutableLiveData.getValue().setUpdated(false));
    }
    


}
