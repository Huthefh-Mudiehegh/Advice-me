package com.huthfy.AdviceMe.data.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Feel implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private final int  id_feel;

    @NonNull
    private final String  name_feel;

    @Ignore
    private int ic_feel;




    public Feel(int id_feel, String name_feel) {
        this.id_feel = id_feel;
        this.name_feel = name_feel;
    }

    public int getIc_feel() {
        return ic_feel;
    }

    public int getId_feel() {
        return id_feel;
    }

    public String getName_feel() {
        return name_feel;
    }

    public void setIc_feel(int ic_feel){
        this.ic_feel = ic_feel;
    }

}
