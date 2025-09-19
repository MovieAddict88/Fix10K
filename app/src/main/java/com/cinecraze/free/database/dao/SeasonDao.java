package com.cinecraze.free.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.cinecraze.free.database.entities.SeasonEntity;
import java.util.List;

@Dao
public interface SeasonDao {
    @Insert
    long insert(SeasonEntity season);

    @Query("SELECT * FROM seasons WHERE entryId = :entryId")
    List<SeasonEntity> getSeasonsForEntry(int entryId);

    @Query("DELETE FROM seasons WHERE entryId = :entryId")
    void deleteSeasonsForEntry(int entryId);
}
