package com.huthfy.AdviceMe.ui.activity.favorite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.huthfy.AdviceMe.R;
import com.huthfy.AdviceMe.data.model.Advice;
import com.huthfy.AdviceMe.ui.listeners.OnAdviceUpdateListener;
import com.huthfy.AdviceMe.ui.activity.favorite.Adapter.FavoriteAdviceAdapter;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class FavoriteActivity extends AppCompatActivity implements OnAdviceUpdateListener {

    public static final String FAV = "FAV";
    FavoriteAdviceViewModel favoriteAdviceViewModel;
    FavoriteAdviceAdapter favoriteAdviceAdapter;
    LinearLayoutCompat noFavourite;
    RecyclerView favoriteRecyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        noFavourite = findViewById(R.id.favorite_noFavorite_LinearLayout);
        favoriteRecyclerview = findViewById(R.id.favorite_recyclerview);

        favoriteAdviceViewModel = new ViewModelProvider(this).get(FavoriteAdviceViewModel.class);


        favoriteAdviceAdapter = new FavoriteAdviceAdapter(this);

        favoriteAdviceViewModel.getFavouriteAdvices();

        favoriteAdviceViewModel.favoriteAdviceUiStateLiveData.observe(FavoriteActivity.this, favoriteAdviceUiState -> {
            Log.d(FAV, "onChanged: ");
            List<Advice> advices = favoriteAdviceUiState.getAdvices();
            if (advices.size() == 0) {
                favoriteRecyclerview.setVisibility(View.GONE);
                noFavourite.setVisibility(View.VISIBLE);
            } else {
                noFavourite.setVisibility(View.GONE);
                favoriteRecyclerview.setVisibility(View.VISIBLE);
                favoriteAdviceAdapter.setList(advices);
            }

        });
        favoriteRecyclerview.setAdapter(favoriteAdviceAdapter);


    }


    @Override
    public void updateAdvice(Advice advice) {
        favoriteAdviceViewModel.updateAdviceFavoriteStatus(advice);
    }
}