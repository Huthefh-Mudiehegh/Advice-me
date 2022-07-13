package com.huthfy.packageByLayer.ui.activity.DisplayAdvice;

import com.huthfy.packageByLayer.data.model.Advice;

import java.util.List;

public class DisplayAdviceUiState {
    private final List<Advice> advices;

    public DisplayAdviceUiState(List<Advice> advices) {
        this.advices = advices;
    }

    public List<Advice> getAdvices() {
        return advices;
    }
}
