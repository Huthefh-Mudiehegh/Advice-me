package com.huthfy.packageByLayer.data.dataSource.remote.api;

import com.huthfy.packageByLayer.data.dataSource.remote.model.AdviceApiModel;
import com.huthfy.packageByLayer.utils.ApiConstant;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;


public interface AdviceApi {

    //TODO: learn RXJava
    //TODO: Q: should return AdviceApiModel or advice

    @GET(ApiConstant.ADVICE)
    Single<AdviceApiModel> getAdvice();
}
