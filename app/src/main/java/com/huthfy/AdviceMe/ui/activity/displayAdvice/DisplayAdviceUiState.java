package com.huthfy.AdviceMe.ui.activity.displayAdvice;

import com.huthfy.AdviceMe.data.model.Advice;
import com.huthfy.AdviceMe.ui.advicesUiState.AdvicesUiState;

import java.util.List;

public class DisplayAdviceUiState extends AdvicesUiState {


    public DisplayAdviceUiState(List<Advice> advices) {
        super(advices);
    }

    public DisplayAdviceUiState(String errorMessage) {
        super(errorMessage);
    }
}
