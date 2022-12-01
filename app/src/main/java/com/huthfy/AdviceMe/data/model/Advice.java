package com.huthfy.AdviceMe.data.model;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(foreignKeys = @ForeignKey(entity = Feel.class,
        parentColumns = "id_feel",
        childColumns = "id_feel_FK")
)
public class Advice implements Serializable {

    @Ignore
    public static final int FAVORITE = 1;
    @Ignore
    public static final int NOT_FAVORITE = 0;

    @Ignore
    public static final String HADEETH = "حديث شريف";

    @Ignore
    public static final String AYA = "آية قرآنية";

    @Ignore
    public static final String ADVICE = "نصيحة";


    @PrimaryKey(autoGenerate = true)
    private int id_advice;

    @NonNull
    @ColumnInfo(defaultValue = "نصيحة")
    private final String title_advice;

    @NonNull
    private final String content_advice;

    private int id_feel_FK;

    private int is_favorite;


    public Advice(int id_advice, @NonNull String title_advice, @NonNull String content_advice, int is_favorite, int id_feel_FK) {
        this.id_advice = id_advice;
        this.title_advice = title_advice;
        this.content_advice = content_advice;
        this.is_favorite = is_favorite;
        this.id_feel_FK = id_feel_FK;
    }

    public int getId_advice() {
        return id_advice;
    }

    @NonNull
    public String getTitle_advice() {
        return title_advice;
    }

    public String getContent_advice() {
        return content_advice;
    }

    public int getId_feel_FK() {
        return id_feel_FK;
    }


    public int is_favorite() {
        return is_favorite;
    }

}
