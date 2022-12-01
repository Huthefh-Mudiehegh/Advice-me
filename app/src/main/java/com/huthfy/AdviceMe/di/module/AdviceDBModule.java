package com.huthfy.AdviceMe.di.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.huthfy.AdviceMe.data.dataSource.local.dao.AdviceDao;
import com.huthfy.AdviceMe.data.dataSource.local.dao.FeelDao;
import com.huthfy.AdviceMe.data.dataSource.local.database.AdviceDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class AdviceDBModule {
    
    private SharedPreferences sharedPreferences;
    @Provides
    AdviceDao provideAdviceDao(AdviceDatabase adviceDatabase) {
        return adviceDatabase.getAdviceDao();
    }

    @Provides
    FeelDao provideFeelDao(AdviceDatabase adviceDatabase) {
        return adviceDatabase.getFeelDao();
    }

    @Provides
    @Singleton
    AdviceDatabase provideAdviceDatabase(@ApplicationContext Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        //check is database not created at first time ?
        if (sharedPreferences.getBoolean("dbCreated", false) == false) {
            // create database just once
            return Room.databaseBuilder(context, AdviceDatabase.class, "adviceDatabase")
                    .fallbackToDestructiveMigration()
                    .createFromAsset("database/AdviceDatabase.db")
                    .addCallback(new RoomDatabase.Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            markDatabaseAsCreated();

                        }
                    })
                    .build();

        }
        else
            // bring the database that was created.
            return Room.databaseBuilder(context, AdviceDatabase.class, "adviceDatabase")
                    .fallbackToDestructiveMigration()
                    .build();
    }


    private void markDatabaseAsCreated() {
        sharedPreferences.edit()
                .putBoolean("dbCreated", true)
                .commit();
    }


}
