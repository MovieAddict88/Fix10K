package com.cinecraze.free.database.entities;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class SeasonWithEpisodes {
    @Embedded
    public SeasonEntity season;

    @Relation(
        parentColumn = "id",
        entityColumn = "seasonId"
    )
    public List<EpisodeEntity> episodes;
}
