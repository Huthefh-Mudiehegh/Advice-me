package com.huthfy.AdviceMe.ui.activity.adviceList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.huthfy.AdviceMe.R;
import com.huthfy.AdviceMe.data.model.Advice;
import com.huthfy.AdviceMe.ui.listeners.OnAdviceEventsListener;
import com.huthfy.AdviceMe.ui.activity.displayAdvice.DisplayAdviceActivity;
import com.huthfy.AdviceMe.ui.activity.favorite.FavoriteActivity;
import com.huthfy.AdviceMe.utils.IntentKeys;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AdviceUpdateListActivity extends AppCompatActivity implements OnAdviceEventsListener {


    private static final String FAV_ACTION = "FAV_ACTION";
    AdviceListAdapter adviceListAdapter;
    private AdviceListViewModel adviceListViewModel;
    String selectedFeel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advice_list);
        setSupportActionBar(findViewById(R.id.adviceList_tool_bar));

        RecyclerView adviceRecyclerview = findViewById(R.id.adviceList_recyclerview);
        adviceListAdapter = new AdviceListAdapter(this);
        adviceListViewModel = new ViewModelProvider(this).get(AdviceListViewModel.class);

        Intent feelData =  getIntent();
        selectedFeel = feelData.getStringExtra(IntentKeys.SELECTED_FEEL);

        adviceRecyclerview.setAdapter(adviceListAdapter);
        adviceRecyclerview.setLayoutManager(new LinearLayoutManager(this));



    }

    @Override
    protected void onResume() {
        super.onResume();
        adviceListViewModel.getAdvicesByFeel(selectedFeel);
        adviceListViewModel.adviceUiStateLiveData.observe(this, adviceListUiState -> {
            Log.d(FAV_ACTION, "onChanged: ");
            //TODO:show advices list when update or first time
            if(adviceListUiState.isUpdated()){
                adviceListAdapter.setArrayList(adviceListUiState.getAdvices());
                Toast.makeText(AdviceUpdateListActivity.this, "تم الاضافة الى المفضلة", Toast.LENGTH_SHORT).show();

            }else
                if (adviceListUiState.isSuccessed()){
                    adviceListAdapter.setArrayList(adviceListUiState.getAdvices());
                }else
                    Toast.makeText(AdviceUpdateListActivity.this, "حصل خطأ ما", Toast.LENGTH_SHORT).show();
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
            Intent intent = new Intent(AdviceUpdateListActivity.this, FavoriteActivity.class);
            startActivity(intent);
            return true;
        }
        return false;
    }

    @Override
    public void onClickAdvice(Advice advice) {
        Bundle adviceBundle = new Bundle();
        adviceBundle.putString(IntentKeys.ADVICE_TITLE,advice.getTitle_advice());
        adviceBundle.putInt(IntentKeys.ADVICE_ID,advice.getId_advice());
        adviceBundle.putString(IntentKeys.SELECTED_FEEL,selectedFeel);
        
        Intent intent = new Intent(AdviceUpdateListActivity.this, DisplayAdviceActivity.class);
        intent.putExtras(adviceBundle);
        startActivity(intent);
    }

    @Override
    public void updateAdvice(Advice advice) {
        // mark the advice in database;
        Log.d(FAV_ACTION, "onMarkedAdviceAsFavorite: activity");

        adviceListViewModel.updateAdviceFavoriteStatus(advice);
        }


}