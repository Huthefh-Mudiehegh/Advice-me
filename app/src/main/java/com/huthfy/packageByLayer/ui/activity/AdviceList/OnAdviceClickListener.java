package com.huthfy.packageByLayer.ui.activity.AdviceList;

import com.huthfy.packageByLayer.data.model.Advice;

public interface OnAdviceClickListener {
    void onClickAdvice(Advice advice);
    void onMarkedAdviceAsFavorite(Advice advice);

}
