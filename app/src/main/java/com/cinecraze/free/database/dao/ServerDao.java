package com.cinecraze.free.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import com.cinecraze.free.database.entities.ServerEntity;

import java.util.List;

import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.cinecraze.free.database.entities.ServerEntity;

@Dao
public interface ServerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ServerEntity server);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<ServerEntity> servers);

    @Query("DELETE FROM servers")
    void deleteAll();
}
