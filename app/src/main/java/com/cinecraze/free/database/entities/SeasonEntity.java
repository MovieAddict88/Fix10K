package com.cinecraze.free.database.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "seasons",
        foreignKeys = @ForeignKey(entity = EntryEntity.class,
                                  parentColumns = "id",
                                  childColumns = "entryId",
                                  onDelete = ForeignKey.CASCADE))
public class SeasonEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public int entryId;

    public String name;

    public int seasonNumber;
}
