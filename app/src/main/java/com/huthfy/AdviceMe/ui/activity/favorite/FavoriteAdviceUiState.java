package com.huthfy.AdviceMe.ui.activity.favorite;

import com.huthfy.AdviceMe.data.model.Advice;
import com.huthfy.AdviceMe.ui.advicesUiState.AdvicesUiState;

import java.util.List;

public class FavoriteAdviceUiState extends AdvicesUiState {

    public FavoriteAdviceUiState(List<Advice> advices) {
        super(advices);
    }

    public FavoriteAdviceUiState(String errorMessage) {
        super(errorMessage);
    }
}
