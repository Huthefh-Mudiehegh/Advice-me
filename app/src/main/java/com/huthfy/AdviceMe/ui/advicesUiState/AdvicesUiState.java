package com.huthfy.AdviceMe.ui.advicesUiState;

import com.huthfy.AdviceMe.data.model.Advice;

import java.util.List;

public class AdvicesUiState {
    private final List<Advice> advices;

    private final String errorMessage;
    private final boolean isSuccessed;
    private  boolean isUpdated = false;

    public AdvicesUiState(List<Advice> advices) {
        this.advices = advices;
        errorMessage = null;
        isSuccessed = true;
    }

    public AdvicesUiState(String errorMessage) {
        this.advices = null;
        this.errorMessage = errorMessage;
        isSuccessed = false;
    }


    public List<Advice> getAdvices() {
        return advices;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public boolean isSuccessed() {
        return isSuccessed;
    }

    public void setUpdated(boolean isUpdated){
        this.isUpdated = isUpdated;
    }

    public boolean isUpdated() {
        return isUpdated;
    }
}
