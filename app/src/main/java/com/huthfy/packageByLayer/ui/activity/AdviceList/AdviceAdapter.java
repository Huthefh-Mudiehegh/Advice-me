package com.huthfy.packageByLayer.ui.activity.AdviceList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.huthfy.packageByLayer.R;
import com.huthfy.packageByLayer.data.model.Advice;

import java.util.ArrayList;

public class AdviceAdapter extends RecyclerView.Adapter<AdviceAdapter.AdviceViewHolder> {

    ArrayList<Advice> arrayList;
    OnAdviceClickListener onAdviceClickListener;
    public AdviceAdapter(OnAdviceClickListener onAdviceClickListener) {
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

        holder.adviceTitle.setText("حديث شريف"+item.getId_advice());
        holder.adviceContent.setText(item.getContent_advice());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public void setArrayList(ArrayList arrayList) {
        this.arrayList = arrayList;
    }

    class AdviceViewHolder extends RecyclerView.ViewHolder {

        TextView adviceTitle,adviceContent;

        public AdviceViewHolder(@NonNull View itemView) {
            super(itemView);
            adviceTitle = itemView.findViewById(R.id.advice_title_TV);
            adviceContent = itemView.findViewById(R.id.advice_content_TV);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onAdviceClickListener.onclick(arrayList.get(getAdapterPosition()),getAdapterPosition());
                }
            });
        }
    }
}
