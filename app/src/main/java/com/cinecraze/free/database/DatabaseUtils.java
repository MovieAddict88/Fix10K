package com.cinecraze.free.database;

import com.cinecraze.free.database.entities.EntryEntity;
import com.cinecraze.free.database.entities.EntryWithDetails;
import com.cinecraze.free.database.entities.EpisodeEntity;
import com.cinecraze.free.database.entities.SeasonEntity;
import com.cinecraze.free.database.entities.SeasonWithEpisodes;
import com.cinecraze.free.database.entities.ServerEntity;
import com.cinecraze.free.models.Entry;
import com.cinecraze.free.models.Episode;
import com.cinecraze.free.models.Server;
import com.cinecraze.free.models.Season;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DatabaseUtils {
    
    private static final Gson gson = new Gson();
    
    /**
     * Convert Entry API model to EntryEntity database entity
     */
    public static EntryEntity entryToEntity(Entry entry, String mainCategory) {
        EntryEntity entity = new EntryEntity();
        
        entity.setTitle(entry.getTitle());
        entity.setSubCategory(entry.getSubCategory());
        entity.setCountry(entry.getCountry());
        entity.setDescription(entry.getDescription());
        entity.setPoster(entry.getPoster());
        entity.setThumbnail(entry.getThumbnail());
        entity.setRating(entry.getRatingString());
        entity.setDuration(entry.getDuration());
        entity.setYear(entry.getYearString());
        entity.setMainCategory(mainCategory);
        
        // Convert complex objects to JSON strings
        entity.setRelatedJson(gson.toJson(entry.getRelated()));
        
        return entity;
    }
    
    /**
     * Convert EntryEntity database entity to Entry API model
     */
    public static Entry entryWithDetailsToEntry(EntryWithDetails entryWithDetails) {
        Entry entry = new Entry();
        EntryEntity entity = entryWithDetails.entry;
        
        // Use proper setter methods
        entry.setTitle(entity.getTitle());
        entry.setSubCategory(entity.getSubCategory());
        entry.setMainCategory(entity.getMainCategory());
        entry.setCountry(entity.getCountry());
        entry.setDescription(entity.getDescription());
        entry.setPoster(entity.getPoster());
        entry.setThumbnail(entity.getThumbnail());
        entry.setRating(entity.getRating());
        entry.setDuration(entity.getDuration());
        entry.setYear(entity.getYear());
        
        // Convert servers
        if (entryWithDetails.servers != null) {
            List<Server> servers = new ArrayList<>();
            for (ServerEntity serverEntity : entryWithDetails.servers) {
                Server server = new Server();
                server.setName(serverEntity.name);
                server.setUrl(serverEntity.url);
                server.setQuality(serverEntity.quality);
                servers.add(server);
            }
            entry.setServers(servers);
        }

        // Convert seasons
        if (entryWithDetails.seasons != null) {
            List<Season> seasons = new ArrayList<>();
            for (SeasonWithEpisodes seasonWithEpisodes : entryWithDetails.seasons) {
                Season season = new Season();
                SeasonEntity seasonEntity = seasonWithEpisodes.season;
                season.setName(seasonEntity.name);
                season.setSeasonNumber(seasonEntity.seasonNumber);

                if (seasonWithEpisodes.episodes != null) {
                    List<Episode> episodes = new ArrayList<>();
                    for (EpisodeEntity episodeEntity : seasonWithEpisodes.episodes) {
                        Episode episode = new Episode();
                        episode.setTitle(episodeEntity.title);
                        episode.setEpisodeNumber(episodeEntity.episodeNumber);
                        episode.setThumbnail(episodeEntity.thumbnail);

                        Type serverListType = new TypeToken<List<Server>>(){}.getType();
                        List<Server> episodeServers = gson.fromJson(episodeEntity.serversJson, serverListType);
                        episode.setServers(episodeServers);
                        episodes.add(episode);
                    }
                    season.setEpisodes(episodes);
                }
                seasons.add(season);
            }
            entry.setSeasons(seasons);
        }

        try {
            Type entryListType = new TypeToken<List<Entry>>(){}.getType();
            List<Entry> related = gson.fromJson(entity.getRelatedJson(), entryListType);
            entry.setRelated(related);
        } catch (Exception e) {
            entry.setRelated(new ArrayList<>());
        }
        
        return entry;
    }
    
    /**
     * Convert list of EntryEntity to list of Entry
     */
    public static List<Entry> entitiesToEntries(List<EntryWithDetails> entriesWithDetails) {
        List<Entry> entries = new ArrayList<>();
        for (EntryWithDetails entryWithDetails : entriesWithDetails) {
            entries.add(entryWithDetailsToEntry(entryWithDetails));
        }
        return entries;
    }
}