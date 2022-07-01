package com.huthfy.packageByLayer.ui.activity.DisplayAdvice;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huthfy.packageByLayer.R;
import com.huthfy.packageByLayer.data.model.Advice;

public class DisplayAdviceFragment extends Fragment {

    private static final String ARG_TITLE = "advice_title";
    private static final String ARG_CONTENT = "advice_content";
    private static final String ARG_POSITION = "advice_position";


    private String adviceTitle;
    private String adviceContent;
    private int advicePosition;

    TextView adviceTitleTV,adviceContentTV,advicePositionTV;

    public DisplayAdviceFragment() {
        // Required empty public constructor
    }


    public static DisplayAdviceFragment newInstance(Advice advice, int advicePosition) {
        DisplayAdviceFragment fragment = new DisplayAdviceFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, advice.getId_advice()+"");
        args.putString(ARG_CONTENT, advice.getContent_advice());
        args.putInt(ARG_POSITION, advicePosition);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            adviceTitle = getArguments().getString(ARG_TITLE);
            adviceContent = getArguments().getString(ARG_CONTENT);
            advicePosition = getArguments().getInt(ARG_POSITION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_display_advice, container, false);
        adviceTitleTV = view.findViewById(R.id.displayAdvice_adviceTitle);
        adviceContentTV = view.findViewById(R.id.displayAdvice_adviceContent);
        advicePositionTV = view.findViewById(R.id.displayAdvice_advicePosition);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adviceTitleTV.setText("حديث شريف");
        adviceContentTV.setText(adviceContent);
        advicePositionTV.setText(advicePosition+"/"+"5");
    }
}