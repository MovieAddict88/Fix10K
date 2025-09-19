package com.cinecraze.free.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.cinecraze.free.database.entities.ServerEntity;
import java.util.List;

@Dao
public interface ServerDao {
    @Insert
    void insertAll(List<ServerEntity> servers);

    @Query("SELECT * FROM servers WHERE entryId = :entryId")
    List<ServerEntity> getServersForEntry(int entryId);

    @Query("DELETE FROM servers WHERE entryId = :entryId")
    void deleteServersForEntry(int entryId);
}
