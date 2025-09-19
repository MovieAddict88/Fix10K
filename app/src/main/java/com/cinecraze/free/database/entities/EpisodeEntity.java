package com.cinecraze.free.database.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "episodes",
        foreignKeys = @ForeignKey(entity = SeasonEntity.class,
                                  parentColumns = "id",
                                  childColumns = "seasonId",
                                  onDelete = ForeignKey.CASCADE))
public class EpisodeEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public int seasonId;

    public String title;

    public int episodeNumber;

    public String thumbnail;

    public String serversJson; // Store servers as JSON string
}
