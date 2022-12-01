package com.huthfy.AdviceMe.ui.activity.favorite.Adapter;

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
import com.huthfy.AdviceMe.ui.activity.favorite.FavoriteActivity;

import java.util.ArrayList;
import java.util.List;

public class FavoriteAdviceAdapter extends RecyclerView.Adapter<FavoriteAdviceAdapter.FavoriteAdviceAdapterViewHolder> {

    List<Advice> List = new ArrayList<>();
    OnAdviceUpdateListener onAdviceUpdateListener;
    int currentAdvicePressed = -1;

    public FavoriteAdviceAdapter(OnAdviceUpdateListener onAdviceUpdateListener) {
        this.onAdviceUpdateListener = onAdviceUpdateListener;
    }

    @NonNull
    @Override
    public FavoriteAdviceAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FavoriteAdviceAdapterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.advice_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteAdviceAdapterViewHolder holder, int position) {
        Advice item = List.get(position);
        holder.adviceTitle.setText(item.getTitle_advice());
        holder.adviceContent.setText(item.getContent_advice());
        holder.favoriteIBtn.setImageResource(R.drawable.ic_favorite_filled_blue);
    }

    @Override
    public int getItemCount() {
        return List.size();
    }


    public void setList(List<Advice> list) {
        this.List = list;

        notifyDataSetChanged();

    }

    class FavoriteAdviceAdapterViewHolder extends RecyclerView.ViewHolder {
        ImageButton favoriteIBtn;
        TextView adviceTitle, adviceContent;

        public FavoriteAdviceAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            favoriteIBtn = itemView.findViewById(R.id.adviceList_favorite_icon);
            adviceTitle = itemView.findViewById(R.id.advice_title_TV);
            adviceContent = itemView.findViewById(R.id.advice_content_TV);
            favoriteIBtn.setOnClickListener(view -> {
                Log.d(FavoriteActivity.FAV, "onClick: favAdvice");
                currentAdvicePressed = getAdapterPosition();

                Advice advice = List.get(currentAdvicePressed);

                Advice newAdvice = new Advice(advice.getId_advice(), advice.getTitle_advice(),
                        advice.getContent_advice(), Advice.NOT_FAVORITE, advice.getId_feel_FK());

                onAdviceUpdateListener.updateAdvice(newAdvice);
            });
        }
    }
}
