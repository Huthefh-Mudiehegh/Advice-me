package com.huthfy.AdviceMe.ui.activity.adviceList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.huthfy.AdviceMe.R;
import com.huthfy.AdviceMe.data.model.Advice;
import com.huthfy.AdviceMe.ui.listeners.OnAdviceEventsListener;

import java.util.ArrayList;
import java.util.List;

public class AdviceListAdapter extends RecyclerView.Adapter<AdviceListAdapter.AdviceViewHolder> {

    List<Advice> arrayList;
    OnAdviceEventsListener onAdviceEventsListener;

    public AdviceListAdapter(OnAdviceEventsListener onAdviceEventsListener) {
        this.arrayList = new ArrayList<>();
        this.onAdviceEventsListener = onAdviceEventsListener;
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
        if (item.is_favorite() == Advice.FAVORITE) {
            holder.favoriteIcon.setImageResource(R.drawable.ic_favorite_filled_blue);
        } else
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

        TextView adviceTitle, adviceContent;
        ImageButton favoriteIcon;

        public AdviceViewHolder(@NonNull View itemView) {
            super(itemView);
            adviceTitle = itemView.findViewById(R.id.advice_title_TV);
            adviceContent = itemView.findViewById(R.id.advice_content_TV);
            favoriteIcon = itemView.findViewById(R.id.adviceList_favorite_icon);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onAdviceEventsListener.onClickAdvice(arrayList.get(getAdapterPosition()));
                }
            });

            favoriteIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Advice advice = arrayList.get(getAdapterPosition());

                    Advice newAdvice;
                    if (advice.is_favorite() == Advice.FAVORITE) {

                        newAdvice = new Advice(advice.getId_advice(), advice.getTitle_advice(),
                                advice.getContent_advice(), Advice.NOT_FAVORITE, advice.getId_feel_FK());
                    
                    } else {
                        newAdvice = new Advice(advice.getId_advice(), advice.getTitle_advice(),
                                advice.getContent_advice(), Advice.FAVORITE, advice.getId_feel_FK());

                    }
                    onAdviceEventsListener.updateAdvice(newAdvice);


                }
            });
        }
    }
}
