package com.huthfy.packageByLayer.data.dataSource.remote.model;


// TODO: should I create model for Api object and also database object and one another for rest of
    //of the app ? or just one is enough

//TODO: should create all fields of the response api


import com.huthfy.packageByLayer.data.model.Advice;

public class AdviceApiModel {
    private final Advice slip;

    public AdviceApiModel(Advice slip) {
        this.slip = slip;
    }

    public Advice getAdvice(){
        return slip;
    }

}
