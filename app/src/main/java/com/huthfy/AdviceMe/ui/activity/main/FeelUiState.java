package com.huthfy.AdviceMe.ui.activity.main;

import com.huthfy.AdviceMe.data.model.Feel;

import java.util.List;

public class FeelUiState {
    private final List<Feel> feels;
   
    public FeelUiState(List<Feel> feels) {
        this.feels = feels;
        
    }
    
    public List<Feel> getFeels() {
        return feels;
    }
}
