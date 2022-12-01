package com.huthfy.AdviceMe.ui.activity.main;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.huthfy.AdviceMe.R;
import com.huthfy.AdviceMe.data.model.Feel;

import java.util.ArrayList;
import java.util.List;

public class FeelAdapter extends RecyclerView.Adapter<FeelAdapter.FeelViewHolder> {

    private List<Feel> arrayList = new ArrayList<>();
    int currentFeelPosition = -1;
    OnFeelCLickListener onFeelCLickListener;

    public FeelAdapter(OnFeelCLickListener onFeelCLickListener) {
        this.onFeelCLickListener = onFeelCLickListener;
    }

    @NonNull
    @Override
    public FeelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FeelViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.feels_item_of_recyclerview, parent, false));
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull FeelViewHolder holder, int position) {
        Feel item = arrayList.get(position);

        holder.feelName.setText(item.getName_feel());
        //holder.feelIcon.setImageResource(item.getIc_feel());
        if (currentFeelPosition == position){
            holder.feelCardView.setBackgroundResource(R.drawable.style_main_selected_feel);
        }else
            holder.feelCardView.setBackgroundResource(R.drawable.style_main_un_selected_feel);


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public void setArrayList(List<Feel> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    class FeelViewHolder extends RecyclerView.ViewHolder {
        TextView feelName;
        CardView feelCardView;
        RelativeLayout relativeLayout;
        ImageButton feelIcon;
        int position;
        public FeelViewHolder(@NonNull View itemView) {
            super(itemView);
            feelName = itemView.findViewById(R.id.feel_name_TV);
            feelCardView = itemView.findViewById(R.id.main_feel_item_cardView);
            relativeLayout =itemView.findViewById(R.id.main_feel_relativeLayout);
            feelIcon = itemView.findViewById(R.id.feel_icon);

            relativeLayout.setOnClickListener(view -> {
                position = getAdapterPosition();
                onFeelCLickListener.onFeelClick(arrayList.get(position),position);
            });
        }
    }

    public int getCurrentFeelPosition() {
        return currentFeelPosition;
    }

    public void setCurrentFeelPosition(int currentFeel) {
        this.currentFeelPosition = currentFeel;

    }

    public interface OnFeelCLickListener {
        void onFeelClick(Feel feel, int position);

    }
}
