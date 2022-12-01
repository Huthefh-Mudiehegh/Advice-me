package com.huthfy.AdviceMe.ui.activity.favorite;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.huthfy.AdviceMe.data.model.Advice;
import com.huthfy.AdviceMe.domain.useCase.GetFavoriteAdvicesUseCase;
import com.huthfy.AdviceMe.domain.useCase.UpdateAdviceFavoriteUseCase;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
public class FavoriteAdviceViewModel extends ViewModel {
    private static final String TAG = "favoriteAdvice";

    private final MutableLiveData<FavoriteAdviceUiState> favoriteAdviceUiStateMutableLiveData = new MutableLiveData<>();
    public LiveData<FavoriteAdviceUiState> favoriteAdviceUiStateLiveData = favoriteAdviceUiStateMutableLiveData;

    private GetFavoriteAdvicesUseCase getFavoriteAdvicesUseCase;
    private UpdateAdviceFavoriteUseCase updateAdviceFavoriteUseCase;

    public FavoriteAdviceViewModel() {
    }

    @Inject
    public FavoriteAdviceViewModel(GetFavoriteAdvicesUseCase getFavoriteAdvicesUseCase,
                                   UpdateAdviceFavoriteUseCase updateAdviceFavoriteUseCase) {

        this.getFavoriteAdvicesUseCase = getFavoriteAdvicesUseCase;
        this.updateAdviceFavoriteUseCase = updateAdviceFavoriteUseCase;
    }

    public void getFavouriteAdvices(){
        if (getFavoriteAdvicesUseCase == null) {
            Log.d(TAG, "getAdvicesUseCase: is "+ getFavoriteAdvicesUseCase);
        }
        Log.d(TAG, "getAdvices: "+ getFavoriteAdvicesUseCase);
        getFavoriteAdvicesUseCase.getFavoriteAdvices().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(advices->favoriteAdviceUiStateMutableLiveData.setValue(new FavoriteAdviceUiState(advices)),
                        error->favoriteAdviceUiStateMutableLiveData.setValue(new FavoriteAdviceUiState(error.getMessage())));
    }

    public void updateAdviceFavoriteStatus(Advice advice) {
        updateAdviceFavoriteUseCase.update(advice)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> favoriteAdviceUiStateMutableLiveData.getValue().setUpdated(true),
                        error -> favoriteAdviceUiStateMutableLiveData.getValue().setUpdated(false));
    }

}
