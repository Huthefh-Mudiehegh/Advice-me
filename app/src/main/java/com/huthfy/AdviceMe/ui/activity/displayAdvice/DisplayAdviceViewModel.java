package com.huthfy.AdviceMe.ui.activity.displayAdvice;



import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.huthfy.AdviceMe.data.model.Advice;
import com.huthfy.AdviceMe.domain.useCase.UpdateAdviceFavoriteUseCase;
import com.huthfy.AdviceMe.domain.useCase.GetAdvicesByFeelFilterTitleUseCase;


import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
public class DisplayAdviceViewModel extends ViewModel {

    private final MutableLiveData<DisplayAdviceUiState> displayAdviceUiStateMutableLiveData = new MutableLiveData<>();
    public LiveData<DisplayAdviceUiState> displayAdviceUiStateLiveData = displayAdviceUiStateMutableLiveData;
    private GetAdvicesByFeelFilterTitleUseCase getAdvicesByFeelFilterTitleUseCase;
    private UpdateAdviceFavoriteUseCase updateAdviceFavoriteUseCase;

    public DisplayAdviceViewModel() {
    }

    @Inject
    public DisplayAdviceViewModel(GetAdvicesByFeelFilterTitleUseCase getAdvicesByFeelFilterTitleUseCase,
                                  UpdateAdviceFavoriteUseCase updateAdviceFavoriteUseCase
    ) {
        this.getAdvicesByFeelFilterTitleUseCase = getAdvicesByFeelFilterTitleUseCase;
        this.updateAdviceFavoriteUseCase = updateAdviceFavoriteUseCase;
    }

    public void getAdvicesByFeelFilterTitle(String feelName,String adviceTitle){
        
        getAdvicesByFeelFilterTitleUseCase.getAdviceByFeelFilterTitle(feelName,adviceTitle)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(advices->displayAdviceUiStateMutableLiveData.setValue(new DisplayAdviceUiState(advices)),
                error->new DisplayAdviceUiState(error.getMessage()));

    }

    public void updateAdviceFavoriteStatus(Advice advice) {
        updateAdviceFavoriteUseCase.update(advice)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> displayAdviceUiStateMutableLiveData.getValue().setUpdated(true),
                        error -> displayAdviceUiStateMutableLiveData.getValue().setUpdated(false));
    }
}
