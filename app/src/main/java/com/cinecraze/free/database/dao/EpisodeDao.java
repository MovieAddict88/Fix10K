package com.cinecraze.free.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.cinecraze.free.database.entities.EpisodeEntity;
import java.util.List;

@Dao
public interface EpisodeDao {
    @Insert
    void insertAll(List<EpisodeEntity> episodes);

    @Query("SELECT * FROM episodes WHERE seasonId = :seasonId")
    List<EpisodeEntity> getEpisodesForSeason(int seasonId);

    @Query("DELETE FROM episodes WHERE seasonId IN (SELECT id FROM seasons WHERE entryId = :entryId)")
    void deleteEpisodesForEntry(int entryId);
}
