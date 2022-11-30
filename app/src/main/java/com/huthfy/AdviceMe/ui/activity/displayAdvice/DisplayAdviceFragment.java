//package com.huthfy.AdviceMe.ui.activity.DisplayAdvice;
//
//import android.content.Context;
//import android.os.Bundle;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageButton;
//import android.widget.TextView;
//
//import com.huthfy.AdviceMe.R;
//import com.huthfy.AdviceMe.data.model.Advice;
//
//import dagger.hilt.android.scopes.FragmentScoped;
//
//@FragmentScoped
//public class DisplayAdviceFragment extends Fragment implements View.OnClickListener {
//
//    private static final String ARG_TITLELE = "advice_title";
//    private static final String ARG_CONTENT = "advice_content";
//    private static final String ARG_IS_FAVORITE = "advice_favorite";
//    private static final String ARG_POSITION = "advice_position";
//    private static final String ARG_SIZE = "advices_size";
//    private static final String ARG_ID = "advice_Id";
//    private static final String ARG_ID_FK = "advice_Id_FK";
//    private static final String markAsFav = "markAsFav";
//
//
//    private String adviceTitle;
//    private int adviceId;
//    private int adviceId_FK;
//    private String adviceContent;
//    private int advicePosition;
//    private int advicesSize;
//    private int isFavoriteAdvice;
//
//    OnMarkAdviceListener onMarkAdviceListener;
//
//    TextView adviceTitleTV,adviceContentTV,advicePositionTV;
//    public ImageButton favoriteIconIB;
//
//
//    public DisplayAdviceFragment() {
//        // Required empty public constructor
//    }
//
//
//    public static DisplayAdviceFragment newInstance(Advice advice, int advicePosition,int size) {
//        DisplayAdviceFragment fragment = new DisplayAdviceFragment();
//        Log.d(markAsFav, "DisplayAdviceFragment: newInstance pass advice: seventh("+advice.is_favorite()+")");
//        Log.d(markAsFav, "DisplayAdviceFragment: create newInstance: seventh("+fragment.isFavoriteAdvice+")");
//
//        Bundle args = new Bundle();
//        args.putInt(ARG_ID, advice.getId_advice());
//        args.putInt(ARG_ID_FK, advice.getId_feel_FK());
//        args.putString(ARG_TITLE, advice.getTitle_advice());
//        args.putString(ARG_CONTENT, advice.getContent_advice());
//        args.putInt(ARG_IS_FAVORITE, advice.is_favorite());
//        args.putInt(ARG_POSITION, advicePosition);
//        args.putInt(ARG_SIZE, size);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onAttach(@NonNull Context context) {
//        super.onAttach(context);
//        onMarkAdviceListener = (OnMarkAdviceListener) context;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            adviceId= getArguments().getInt(ARG_ID);
//            adviceId_FK= getArguments().getInt(ARG_ID_FK);
//            adviceTitle = getArguments().getString(ARG_TITLE);
//            adviceContent = getArguments().getString(ARG_CONTENT);
//            advicePosition = getArguments().getInt(ARG_POSITION);
//            advicesSize = getArguments().getInt(ARG_SIZE);
//            isFavoriteAdvice = getArguments().getInt(ARG_IS_FAVORITE);
//            Log.d(markAsFav, "DisplayAdviceFragment: onCreate fragment: eighth("+isFavoriteAdvice+")");
//
//        }
//
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//
//        View view = inflater.inflate(R.layout.fragment_display_advice, container, false);
//        adviceTitleTV = view.findViewById(R.id.displayAdvice_adviceTitle);
//        adviceContentTV = view.findViewById(R.id.displayAdvice_adviceContent);
//        advicePositionTV = view.findViewById(R.id.displayAdvice_advicePosition);
//        favoriteIconIB = view.findViewById(R.id.displayAdvice_favorite_icon);
//        Log.d(markAsFav, "onCreateView: displayAdviceFragment");
//        return view;
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        adviceTitleTV.setText(adviceTitle);
//        Log.d(markAsFav, "onViewCreated: displayAdviceFragment");
////        if (adviceContent.length()>100){
////            adviceContentTV.setGravity(Gravity.TOP);
////        }
//        adviceContentTV.setText(adviceContent);
//        advicePositionTV.setText(advicePosition+"/"+advicesSize);
//        favoriteIconIB.setOnClickListener(this::onClick);
//        if (isFavoriteAdvice == Advice.FAVORITE){
//            Log.d(DisplayAdviceActivity.markAsFav, "onViewCreated: on fragment is favourite"+isFavoriteAdvice);
//            favoriteIconIB.setImageResource(R.drawable.ic_favorite_filled_blue);
//        }else
//            favoriteIconIB.setImageResource(R.drawable.ic_favorite_outlined);
//
//    }
//
//    @Override
//    public void onClick(View view) {
//        // mark the advice in database;
//        if (isFavoriteAdvice == Advice.FAVORITE){
//            Log.d(markAsFav, "DisplayAdviceFragment: onClick: first("+isFavoriteAdvice+") Un mark as fav");
//            Advice newAdvice = new Advice(adviceId,adviceTitle,
//                    adviceContent,Advice.NOT_FAVORITE,adviceId_FK);
//            //unMark advice
//            onMarkAdviceListener.onUnMark(newAdvice);
//        }
//        else {
//            Log.d(markAsFav, "DisplayAdviceFragment: onClick: first("+isFavoriteAdvice+") mark as fav");
//
//            Advice newAdvice = new Advice(adviceId,adviceTitle,
//                    adviceContent,Advice.FAVORITE,adviceId_FK);
//            onMarkAdviceListener.onMark(newAdvice);
//
//        }
//    }
//}