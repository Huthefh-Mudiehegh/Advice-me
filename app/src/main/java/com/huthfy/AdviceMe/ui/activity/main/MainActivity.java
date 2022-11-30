package com.huthfy.AdviceMe.ui.activity.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.huthfy.AdviceMe.R;
import com.huthfy.AdviceMe.data.model.Feel;
import com.huthfy.AdviceMe.ui.activity.adviceList.AdviceUpdateListActivity;
import com.huthfy.AdviceMe.ui.activity.favorite.FavoriteActivity;
import com.huthfy.AdviceMe.utils.IntentKeys;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity implements FeelAdapter.OnFeelCLickListener {
    
    int previousFeelPosition = 0;
    String currentFeelSelected;

    FeelAdapter feelAdapter;

    FeelViewModel feelViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(findViewById(R.id.main_tool_bar));

        RecyclerView feelRecyclerview = findViewById(R.id.main_feel_recyclerview);
        AppCompatButton adviceMeBtn = findViewById(R.id.main_advice_me_btn);

        feelAdapter = new FeelAdapter(this);

        feelViewModel = new ViewModelProvider(this).get(FeelViewModel.class);
        feelViewModel.getAllFeels();
        feelViewModel.feelUiStateLiveData.observe(this,
                feelsUiState -> feelAdapter.setArrayList(feelsUiState.getFeels()));
        
        feelRecyclerview.setAdapter(feelAdapter);
        feelRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        
        adviceMeBtn.setOnClickListener(view -> {
            if (currentFeelSelected != null){

                Intent adviceListIntent = new Intent(MainActivity.this, AdviceUpdateListActivity.class);
                adviceListIntent.putExtra(IntentKeys.SELECTED_FEEL,currentFeelSelected);
                startActivity(adviceListIntent);
            }else
                Toast.makeText(MainActivity.this, R.string.choose_feel_first, Toast.LENGTH_SHORT).show();

        });

        
    }

    // create option menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_menu,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.favorite_itemMenu){
            //go to favorite activity
            Intent intent = new Intent(MainActivity.this, FavoriteActivity.class);
            startActivity(intent);
            return true;
        }
        return false;
    }

    @Override
    public void onFeelClick(Feel feel, int position) {
        currentFeelSelected = feel.getName_feel();
        changePositionItemClicked(position);
    }

    private void changePositionItemClicked(int position) {

        //get the previous selected feel position
        previousFeelPosition = feelAdapter.getCurrentFeelPosition();

        //check if different feel is clicked
        if (previousFeelPosition != position) {
            //change the previous feel style
            feelAdapter.notifyItemChanged(previousFeelPosition);

            previousFeelPosition = position;
            feelAdapter.setCurrentFeelPosition(position);

            //change the new feel style
            feelAdapter.notifyItemChanged(position);
        }
    }
}