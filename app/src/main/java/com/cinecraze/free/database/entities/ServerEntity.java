package com.cinecraze.free.database.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "servers",
        foreignKeys = @ForeignKey(entity = EntryEntity.class,
                                  parentColumns = "id",
                                  childColumns = "entryId",
                                  onDelete = ForeignKey.CASCADE),
        indices = {@Index("entryId")})
public class ServerEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public int entryId;

    public String name;

    public String url;

    public String quality;
}
