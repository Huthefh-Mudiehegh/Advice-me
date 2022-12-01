package com.huthfy.AdviceMe.ui.activity.adviceList;

import com.huthfy.AdviceMe.data.model.Advice;
import com.huthfy.AdviceMe.ui.advicesUiState.AdvicesUiState;

import java.util.List;


public class AdviceListUiState extends AdvicesUiState {

    public AdviceListUiState(List<Advice> advices) {
        super(advices);
    }

    public AdviceListUiState(String errorMessage) {
        super(errorMessage);
    }
}
