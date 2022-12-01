package com.huthfy.AdviceMe.data.dataSource.local.database;
/* steps
1- make this class in singleton pattern
2- declare Dao interface
3- #done#
 */

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.huthfy.AdviceMe.data.dataSource.local.dao.AdviceDao;
import com.huthfy.AdviceMe.data.dataSource.local.dao.FeelDao;
import com.huthfy.AdviceMe.data.model.Advice;
import com.huthfy.AdviceMe.data.model.Feel;

@Database(entities = {Advice.class, Feel.class}, version = 3, exportSchema = true)
public abstract class AdviceDatabase extends RoomDatabase {

    public abstract AdviceDao getAdviceDao();
    public abstract FeelDao getFeelDao();



}
