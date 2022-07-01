package com.huthfy.packageByLayer.ui.activity.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.huthfy.packageByLayer.R;
import com.huthfy.packageByLayer.data.model.Advice;
import com.huthfy.packageByLayer.data.model.Feel;
import com.huthfy.packageByLayer.ui.activity.AdviceList.AdviceListActivity;
import com.huthfy.packageByLayer.ui.activity.Favorite.FavoriteActivity;
import com.huthfy.packageByLayer.ui.uiState.AdviceUiState;
import com.huthfy.packageByLayer.ui.uiState.FeelUiState;
import com.huthfy.packageByLayer.ui.viewModel.AdviceViewModel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MyTag";
    private static final String FEELS = "MyTag";
    AdviceViewModel adviceViewModel;
    FeelAdapter feelAdapter;
    private RecyclerView feelRecyclerview;
    private AppCompatButton adviceMeBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(findViewById(R.id.main_tool_bar));

        feelRecyclerview = findViewById(R.id.main_feel_recyclerview);
        adviceMeBtn = findViewById(R.id.main_advice_me_btn);

        feelAdapter = new FeelAdapter();
        ArrayList<Feel> feelArrayList = new ArrayList<>();
        feelArrayList.add(new Feel(1,"سعيد"));
        feelArrayList.add(new Feel(1,"سعيد"));
        feelArrayList.add(new Feel(1,"سعيد"));
        feelArrayList.add(new Feel(1,"سعيد"));
        feelArrayList.add(new Feel(1,"سعيد"));
        feelArrayList.add(new Feel(1,"سعيد"));
        feelArrayList.add(new Feel(1,"سعيد"));
        feelArrayList.add(new Feel(1,"سعيد"));
        feelArrayList.add(new Feel(1,"سعيد"));
        feelArrayList.add(new Feel(1,"سعيد"));
        feelArrayList.add(new Feel(1,"سعيد"));
        feelArrayList.add(new Feel(1,"سعيد"));
        feelArrayList.add(new Feel(1,"سعيد"));
        feelArrayList.add(new Feel(1,"سعيد"));

        feelAdapter.setArrayList(feelArrayList);

        feelRecyclerview.setAdapter(feelAdapter);
        feelRecyclerview.setLayoutManager(new LinearLayoutManager(this));


        adviceMeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent adviceListIntent = new Intent(MainActivity.this, AdviceListActivity.class);
                startActivity(adviceListIntent);

            }
        });





//        adviceViewModel = new ViewModelProvider(this).get(AdviceViewModel.class);
//
//        //TODO: observe onChanged method called many times when no data changed.
//        adviceBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                adviceTxt.setText("");
//                markFavoriteBtn.setVisibility(View.INVISIBLE);
////                Log.d(TAG, "onClick: feel>>>"+adviceViewModel.feelUiStateLiveData.getValue().getCurrentFeelSelected());
//                String currentFeelSelected = adviceViewModel.feelUiStateLiveData.getValue().getCurrentFeelSelected();
//                if (currentFeelSelected != null) {
//                    adviceProgressBar.setVisibility(View.VISIBLE);
//                    adviceViewModel.getAdviceByFeel(currentFeelSelected);
//                } else
//                    Toast.makeText(MainActivity.this, "Select your feel first", Toast.LENGTH_LONG).show();
//
//
//            }
//        });
//
//        markFavoriteBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Advice advice = adviceViewModel.adviceUiStateLiveData.getValue().getAdvice();
//                //check if the advice is already marked as favorite
//                if (advice.getIs_favorite() == Advice.FAVORITE) {
//                    // update the advice with unmarked
//                    Toast.makeText(MainActivity.this, "unmarked", Toast.LENGTH_SHORT).show();
//                    adviceViewModel.updateAdvice(
//                            new Advice(advice.getId_advice(), advice.getContent_advice(), Advice.NOT_FAVORITE, advice.getId_feel_FK())
//                    );
//
//                }else {
//                    // mark the advice as favorite
//                    Toast.makeText(MainActivity.this, "marked", Toast.LENGTH_SHORT).show();
//                    adviceViewModel.updateAdvice(
//                            new Advice(advice.getId_advice(), advice.getContent_advice(), Advice.FAVORITE, advice.getId_feel_FK())
//                    );
//                }
//
//            }
//        });
//
//        goToFavoriteBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(MainActivity.this, FavoriteActivity.class));
//            }
//        });
//
//        adviceViewModel.getAllFeels();
//
//        adviceViewModel.adviceUiStateLiveData.observe(MainActivity.this, new Observer<AdviceUiState>() {
//            @Override
//            public void onChanged(AdviceUiState adviceUiState) {
//
//                if (adviceUiState.isSuccessed()) {
////                    Log.d(TAG, "onChanged: advice:" + adviceUiState.getAdvice().getContent_advice());
//                    adviceProgressBar.setVisibility(View.GONE);
//                    adviceTxt.setText(adviceUiState.getAdvice().getContent_advice());
//                    markFavoriteBtn.setVisibility(View.VISIBLE);
//
//                    if (adviceUiState.getAdvice().getIs_favorite() == Advice.FAVORITE){
//                        markFavoriteBtn.setImageResource(R.drawable.ic_is_favorite);
//                    }else {
//                        markFavoriteBtn.setImageResource(R.drawable.ic_not_favorite);
//                    }
//
//
//                } else {
//                    Toast.makeText(MainActivity.this, "No advice found!", Toast.LENGTH_SHORT).show();
//                    adviceProgressBar.setVisibility(View.GONE);
////                    Log.d(TAG, "onChanged: Error:default advice");
//
//                }
//            }
//        });
//
//        adviceViewModel.feelUiStateLiveData.observe(MainActivity.this, new Observer<FeelUiState>() {
//            List<String> feels = new ArrayList<>();
//
//            @Override
//            public void onChanged(FeelUiState feelUiState) {
//                for (Feel feel : feelUiState.getFeels()) {
//                    Log.d(TAG, "onChanged: " + feel.getName_feel());
//                    feels.add(feel.getName_feel());
//                }
//                ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this,
//                        R.layout.feels_dropdown_item, feels);
//                feelsTextView.setAdapter(arrayAdapter);
//            }
//        });
//
//        feelsTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Log.d(TAG, "onItemSelected: " + adapterView.getAdapter().getItem(i));
//                adviceViewModel.feelUiStateLiveData.getValue().setCurrentFeelSelected((String) adapterView.getAdapter().getItem(i));
//
//            }
//        });
//

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
            return true;
        }
        return false;
    }
}