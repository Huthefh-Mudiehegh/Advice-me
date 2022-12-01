package com.huthfy.AdviceMe.ui.activity.displayAdvice;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.huthfy.AdviceMe.R;
import com.huthfy.AdviceMe.data.model.Advice;
import com.huthfy.AdviceMe.ui.listeners.OnAdviceUpdateListener;

import java.util.ArrayList;
import java.util.List;

public class DisplayAdviceAdapter extends RecyclerView.Adapter<DisplayAdviceAdapter.DisplayAdviceViewHolder>{

    List<Advice> list = new ArrayList<>();
    OnAdviceUpdateListener onAdviceUpdateListener;

    public DisplayAdviceAdapter(OnAdviceUpdateListener onAdviceUpdateListener) {
        this.onAdviceUpdateListener = onAdviceUpdateListener;
    }

    @NonNull
    @Override
    public DisplayAdviceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DisplayAdviceViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_display_advice, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DisplayAdviceViewHolder holder, int position) {
        Advice item = list.get(position);
        holder.adviceTitleTV.setText(item.getTitle_advice());
        holder.adviceContentTV.setText(item.getContent_advice());
        holder.advicePositionTV.setText((position+1)+"/"+list.size());
        holder.favoriteIconIB.setOnClickListener(view -> {
            Advice newAdvice;
            if (item.is_favorite() == Advice.FAVORITE){
                //unMark advice
                newAdvice = new Advice(item.getId_advice(),item.getTitle_advice(),
                        item.getContent_advice(),Advice.NOT_FAVORITE,item.getId_feel_FK());
            }else {
                //Mark advice
                newAdvice = new Advice(item.getId_advice(),item.getTitle_advice(),
                        item.getContent_advice(),Advice.FAVORITE,item.getId_feel_FK());
            }
            onAdviceUpdateListener.updateAdvice(newAdvice);
        });

        if (item.is_favorite() == Advice.FAVORITE){
            Log.d(DisplayAdviceActivity.markAsFav, "onViewCreated: on fragment is favourite"+item.is_favorite());
            holder.favoriteIconIB.setImageResource(R.drawable.ic_favorite_filled_blue);
        }else
            holder.favoriteIconIB.setImageResource(R.drawable.ic_favorite_outlined);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public void setList(List<Advice> list) {
        this.list = list;
        notifyDataSetChanged();
    }




    class DisplayAdviceViewHolder extends RecyclerView.ViewHolder {
        TextView adviceTitleTV,adviceContentTV,advicePositionTV;
        ImageButton favoriteIconIB;

        public DisplayAdviceViewHolder(@NonNull View itemView) {
            super(itemView);
            adviceTitleTV = itemView.findViewById(R.id.displayAdvice_adviceTitle);
            adviceContentTV = itemView.findViewById(R.id.displayAdvice_adviceContent);
            advicePositionTV = itemView.findViewById(R.id.displayAdvice_advicePosition);
            favoriteIconIB = itemView.findViewById(R.id.displayAdvice_favorite_icon);


        }
    }
}
