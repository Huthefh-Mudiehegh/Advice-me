package com.huthfy.AdviceMe.data.dataSource.local.dao;

import androidx.room.Dao;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.huthfy.AdviceMe.data.model.Advice;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface AdviceDao {

    @Update(onConflict = OnConflictStrategy.REPLACE)
    Completable updateAdvice(Advice advice);

    @Query("SELECT * FROM Advice JOIN Feel on id_feel_FK = id_feel WHERE name_feel =:feelName")
    Observable<List<Advice>> getAdvicesByFeel(String feelName);

    @Query("SELECT * FROM Advice JOIN Feel on id_feel_FK = id_feel WHERE name_feel =:feelName AND Advice.title_advice =:adviceTitle")
    Observable<List<Advice>> getAdvicesByFeelFilterTitle(String feelName,String adviceTitle);


    @Query("SELECT * FROM Advice WHERE is_favorite !=0")
    Observable<List<Advice>> getFavoriteAdvices();



}
