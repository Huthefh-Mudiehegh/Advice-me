package com.huthfy.AdviceMe.ui.activity.displayAdvice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import com.huthfy.AdviceMe.R;
import com.huthfy.AdviceMe.data.model.Advice;
import com.huthfy.AdviceMe.ui.listeners.OnAdviceUpdateListener;
import com.huthfy.AdviceMe.ui.activity.favorite.FavoriteActivity;
import com.huthfy.AdviceMe.utils.IntentKeys;
import java.util.ArrayList;
import java.util.List;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DisplayAdviceActivity extends AppCompatActivity implements OnAdviceUpdateListener {
    ViewPager2 adviceViewPager;
    ImageButton backArrow, nextArrow;
    DisplayAdviceViewModel displayAdviceViewModel;
    DisplayAdviceAdapter displayAdviceAdapter;

    List<Advice> advices;
    int advicesSize;
    int currentAdvice;
    int adviceId;
    boolean openScreenForFirstTime= true;
    public static final String markAsFav = "markAsFav";
    public static final String current = "current";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_advice);
        setSupportActionBar(findViewById(R.id.displayAdvice_tool_bar));
        adviceViewPager = findViewById(R.id.displayAdvice_viewpager);
        backArrow = findViewById(R.id.displayAdvice_backArrow_IBtn);
        nextArrow = findViewById(R.id.displayAdvice_nextArrow_IBtn);

        displayAdviceViewModel = new ViewModelProvider(this).get(DisplayAdviceViewModel.class);
        Bundle adviceBundle = getIntent().getExtras();
        String feelName = adviceBundle.getString(IntentKeys.SELECTED_FEEL);
        String adviceTitle = adviceBundle.getString(IntentKeys.ADVICE_TITLE);
        adviceId = adviceBundle.getInt(IntentKeys.ADVICE_ID, 0);

        displayAdviceViewModel.getAdvicesByFeelFilterTitle(feelName, adviceTitle);

        advices = new ArrayList<>();

        displayAdviceAdapter = new DisplayAdviceAdapter(this);

        displayAdviceViewModel.displayAdviceUiStateLiveData.observe(this, displayAdviceUiState -> {
            advices = displayAdviceUiState.getAdvices();
            advicesSize = advices.size();
            displayAdviceAdapter.setList(advices);

            if (openScreenForFirstTime){
                for (int i = 0; i < advicesSize; i++) {
                    if (advices.get(i).getId_advice() == adviceId) {
                        currentAdvice = i;
                        break;
                    }
                }
                adviceViewPager.setCurrentItem(currentAdvice, true);
                openScreenForFirstTime = false;
            }

        });
        adviceViewPager.setAdapter(displayAdviceAdapter);

        backArrow.setOnClickListener(view -> {
            currentAdvice = adviceViewPager.getCurrentItem();
            adviceViewPager.setCurrentItem(currentAdvice - 1, true);
        });
        nextArrow.setOnClickListener(view -> {

            currentAdvice = adviceViewPager.getCurrentItem();
            adviceViewPager.setCurrentItem(currentAdvice + 1, true);

        });

    }

    // create option menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.favorite_itemMenu) {
            //go to favorite activity
            Intent intent = new Intent(DisplayAdviceActivity.this, FavoriteActivity.class);
            startActivity(intent);
            return true;
        }
        return false;
    }


    @Override
    public void updateAdvice(Advice advice) {
        displayAdviceViewModel.updateAdviceFavoriteStatus(advice);
    }
}