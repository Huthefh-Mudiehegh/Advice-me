package com.huthfy.packageByLayer.ui.activity.DisplayAdvice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.huthfy.packageByLayer.R;
import com.huthfy.packageByLayer.data.model.Advice;
import com.huthfy.packageByLayer.ui.activity.AdviceList.AdviceListActivity;
import com.huthfy.packageByLayer.ui.activity.Favorite.FavoriteActivity;
import com.huthfy.packageByLayer.utils.IntentKeys;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DisplayAdviceActivity extends AppCompatActivity {
    AdvicePagerAdapter advicePagerAdapter;
    ViewPager2 adviceViewPager;
    ImageButton backArrow, nextArrow;
    DisplayAdviceViewModel displayAdviceViewModel;
    ArrayList<Fragment> fragmentArrayList;

    int adviceId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_advice);
        setSupportActionBar(findViewById(R.id.displayAdvice_tool_bar));

        displayAdviceViewModel = new ViewModelProvider(this).get(DisplayAdviceViewModel.class);
        Intent intent = getIntent();
        String feelName = intent.getStringExtra(IntentKeys.SELECTED_FEEL);
        String adviceTitle = intent.getStringExtra(IntentKeys.ADVICE_TITLE);

        adviceId = intent.getIntExtra(IntentKeys.ADVICE_ID,1);

        displayAdviceViewModel.getAdvicesByFeelFilterTitle(feelName, adviceTitle);

        fragmentArrayList = new ArrayList<>();
        advicePagerAdapter = new AdvicePagerAdapter(this, fragmentArrayList);
        displayAdviceViewModel.displayAdviceUiStateLiveData.observe(this, new Observer<DisplayAdviceUiState>() {
            @Override
            public void onChanged(DisplayAdviceUiState displayAdviceUiState) {
//                Toast.makeText(DisplayAdviceActivity.this, displayAdviceUiState.getAdvices().get(0).getTitle_advice(), Toast.LENGTH_SHORT).show();
                updateDisplayAdviceUi(displayAdviceUiState.getAdvices());


            }
        });


        adviceViewPager = findViewById(R.id.displayAdvice_viewpager);
        backArrow = findViewById(R.id.displayAdvice_backArrow_IBtn);
        nextArrow = findViewById(R.id.displayAdvice_nextArrow_IBtn);

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentAdvice = adviceViewPager.getCurrentItem();
                adviceViewPager.setCurrentItem(currentAdvice - 1, true);
            }
        });
        nextArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int currentAdvice = adviceViewPager.getCurrentItem();
                adviceViewPager.setCurrentItem(currentAdvice + 1, true);
            }
        });


        adviceViewPager.setAdapter(advicePagerAdapter);
//        new TabLayoutMediator(adviceTabLayout, adviceViewPager, new TabLayoutMediator.TabConfigurationStrategy() {
//            @Override
//            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
//                if (position == 1)
//                    tab.setIcon(R.drawable.ic_back_arrow);
//                else
//                    tab.setIcon(R.drawable.ic_next_arrow);
//
//            }
//        }).attach();
    }

    private void updateDisplayAdviceUi(List<Advice> advices) {
        int size = advices.size();
        for (int i = 0; i < size; i++) {
            fragmentArrayList.add(DisplayAdviceFragment.newInstance(advices.get(i), i+1,size));
            if (advices.get(i).getId_advice() == adviceId){
                adviceViewPager.setCurrentItem(i, true);

            }
        }
        advicePagerAdapter.setFragments(fragmentArrayList);


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
}