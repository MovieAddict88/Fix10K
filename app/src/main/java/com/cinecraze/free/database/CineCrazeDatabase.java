package com.cinecraze.free.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.cinecraze.free.database.dao.EntryDao;
import com.cinecraze.free.database.dao.CacheMetadataDao;
import com.cinecraze.free.database.dao.DownloadItemDao;
import com.cinecraze.free.database.dao.ServerDao;
import com.cinecraze.free.database.dao.SeasonDao;
import com.cinecraze.free.database.dao.EpisodeDao;
import com.cinecraze.free.database.entities.EntryEntity;
import com.cinecraze.free.database.entities.CacheMetadataEntity;
import com.cinecraze.free.database.entities.ServerEntity;
import com.cinecraze.free.database.entities.SeasonEntity;
import com.cinecraze.free.database.entities.EpisodeEntity;

@Database(
    entities = {EntryEntity.class, CacheMetadataEntity.class, com.cinecraze.free.database.entities.DownloadItemEntity.class, ServerEntity.class, SeasonEntity.class, EpisodeEntity.class},
    version = 3,
    exportSchema = false
)
public abstract class CineCrazeDatabase extends RoomDatabase {
    
    private static final String DATABASE_NAME = "cinecraze_database";
    private static CineCrazeDatabase instance;
    
    public abstract EntryDao entryDao();
    public abstract CacheMetadataDao cacheMetadataDao();
    public abstract DownloadItemDao downloadItemDao();
    public abstract ServerDao serverDao();
    public abstract SeasonDao seasonDao();
    public abstract EpisodeDao episodeDao();
    
    public static synchronized CineCrazeDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                context.getApplicationContext(),
                CineCrazeDatabase.class,
                DATABASE_NAME
            )
            .fallbackToDestructiveMigration()
            .build();
        }
        return instance;
    }
}