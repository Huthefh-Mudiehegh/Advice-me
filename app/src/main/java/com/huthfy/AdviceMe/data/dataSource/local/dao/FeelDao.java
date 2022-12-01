package com.huthfy.AdviceMe.data.dataSource.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.huthfy.AdviceMe.data.model.Feel;

import java.util.List;

import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface FeelDao {

    @Query("SELECT * FROM Feel")
    Single<List<Feel>> getAllFeels();
    
}
