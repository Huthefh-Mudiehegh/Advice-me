package com.huthfy.packageByLayer.ui.activity.AdviceList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.huthfy.packageByLayer.R;
import com.huthfy.packageByLayer.data.model.Advice;

import java.util.ArrayList;
import java.util.List;

public class AdviceListAdapter extends RecyclerView.Adapter<AdviceListAdapter.AdviceViewHolder> {

    List<Advice> arrayList;
    OnAdviceClickListener onAdviceClickListener;
    public AdviceListAdapter(OnAdviceClickListener onAdviceClickListener) {
        this.arrayList = new ArrayList<>();
        this.onAdviceClickListener = onAdviceClickListener;
    }

    @NonNull
    @Override
    public AdviceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdviceViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.advice_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdviceViewHolder holder, int position) {
        Advice item = arrayList.get(position);

        holder.adviceTitle.setText(item.getTitle_advice());
        holder.adviceContent.setText(item.getContent_advice());
        if (item.getIs_favorite() == Advice.FAVORITE){
            holder.favoriteIcon.setImageResource(R.drawable.ic_favorite_filled_blue);
        }else
            holder.favoriteIcon.setImageResource(R.drawable.ic_favorite_outlined);


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public void setArrayList(List<Advice> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();

    }

    class AdviceViewHolder extends RecyclerView.ViewHolder {

        TextView adviceTitle,adviceContent;
        ImageButton favoriteIcon;
        public AdviceViewHolder(@NonNull View itemView) {
            super(itemView);
            adviceTitle = itemView.findViewById(R.id.advice_title_TV);
            adviceContent = itemView.findViewById(R.id.advice_content_TV);
            favoriteIcon = itemView.findViewById(R.id.adviceList_favorite_icon);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onAdviceClickListener.onClickAdvice(arrayList.get(getAdapterPosition()));
                }
            });

            favoriteIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onAdviceClickListener.onMarkedAdviceAsFavorite(arrayList.get(getAdapterPosition()));
                }
            });
        }
    }
}
