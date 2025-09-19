package com.cinecraze.free.database.entities;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class EntryWithDetails {
    @Embedded
    public EntryEntity entry;

    @Relation(
        entity = SeasonEntity.class,
        parentColumn = "id",
        entityColumn = "entryId"
    )
    public List<ServerEntity> servers;

    @Relation(
        parentColumn = "id",
        entityColumn = "entryId"
    )
    public List<SeasonWithEpisodes> seasons;
}
