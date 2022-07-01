package com.huthfy.packageByLayer.ui.activity.DisplayAdvice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.huthfy.packageByLayer.R;
import com.huthfy.packageByLayer.data.model.Advice;

import java.util.ArrayList;

public class DisplayAdviceActivity extends AppCompatActivity {
    AdvicePagerAdapter advicePagerAdapter;
    ViewPager2 adviceViewPager;
    ImageButton backArrow,nextArrow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_advice);

        ArrayList<Fragment> fragmentArrayList = new ArrayList<>();

        Advice advice = new Advice(2,"قال رسول الله صلى الله عليه وسلم :(خيركم من تعلم القرآن وعلمه)+قال رسول الله صلى الله عليه وسلم :(خيركم من تعلم القرآن وعلمه)+قال رسول الله صلى الله عليه وسلم :(خيركم من تعلم القرآن وعلمه)+قال رسول الله صلى الله عليه وسلم :(خيركم من تعلم القرآن وعلمه)+قال رسول الله صلى الله عليه وسلم :(خيركم من تعلم القرآن وعلمه)+قال رسول الله صلى الله عليه وسلم :(خيركم من تعلم القرآن وعلمه)+قال رسول الله صلى الله عليه وسلم :(خيركم من تعلم القرآن وعلمه)+قال رسول الله صلى الله عليه وسلم :(خيركم من تعلم القرآن وعلمه)+قال رسول الله صلى الله عليه وسلم :(خيركم من تعلم القرآن وعلمه)+قال رسول الله صلى الله عليه وسلم :(خيركم من تعلم القرآن وعلمه)+قال رسول الله صلى الله عليه وسلم :(خيركم من تعلم القرآن وعلمه)+قال رسول الله صلى الله عليه وسلم :(خيركم من تعلم القرآن وعلمه)+قال رسول الله صلى الله عليه وسلم :(خيركم من تعلم القرآن وعلمه)+قال رسول الله صلى الله عليه وسلم :(خيركم من تعلم القرآن وعلمه)",
                1,3);
        fragmentArrayList.add(DisplayAdviceFragment.newInstance(advice,1));
        fragmentArrayList.add(DisplayAdviceFragment.newInstance(advice,2));
        fragmentArrayList.add(DisplayAdviceFragment.newInstance(advice,3));
        fragmentArrayList.add(DisplayAdviceFragment.newInstance(advice,4));

        advicePagerAdapter = new AdvicePagerAdapter(this,fragmentArrayList);

        adviceViewPager = findViewById(R.id.displayAdvice_viewpager);
        backArrow = findViewById(R.id.displayAdvice_backArrow_IBtn);
        nextArrow = findViewById(R.id.displayAdvice_nextArrow_IBtn);

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentAdvice = adviceViewPager.getCurrentItem();
                adviceViewPager.setCurrentItem(currentAdvice-1,true);
            }
        });
        nextArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int currentAdvice = adviceViewPager.getCurrentItem();
                adviceViewPager.setCurrentItem(currentAdvice+1,true);
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
}