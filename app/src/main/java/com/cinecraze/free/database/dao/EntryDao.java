package com.cinecraze.free.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.cinecraze.free.database.entities.EntryEntity;
import com.cinecraze.free.database.entities.EntryWithDetails;

import java.util.List;

@Dao
public interface EntryDao {
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(EntryEntity entry);
    
    @Transaction
    @Query("SELECT * FROM entries")
    List<EntryWithDetails> getAllEntries();
    
    @Transaction
    @Query("SELECT * FROM entries WHERE main_category = :category")
    List<EntryWithDetails> getEntriesByCategory(String category);
    
    @Transaction
    @Query("SELECT * FROM entries WHERE title LIKE '%' || :title || '%'")
    List<EntryWithDetails> searchByTitle(String title);
    
    @Query("SELECT COUNT(*) FROM entries")
    int getEntriesCount();
    
    @Query("DELETE FROM entries")
    void deleteAll();
    
    @Query("DELETE FROM entries WHERE main_category = :category")
    void deleteByCategory(String category);
    
    // Pagination queries
    @Transaction
    @Query("SELECT * FROM entries ORDER BY title ASC LIMIT :limit OFFSET :offset")
    List<EntryWithDetails> getEntriesPaged(int limit, int offset);
    
    @Transaction
    @Query("SELECT * FROM entries WHERE main_category = :category ORDER BY title ASC LIMIT :limit OFFSET :offset")
    List<EntryWithDetails> getEntriesByCategoryPaged(String category, int limit, int offset);
    
    @Transaction
    @Query("SELECT * FROM entries WHERE title LIKE '%' || :title || '%' ORDER BY title ASC LIMIT :limit OFFSET :offset")
    List<EntryWithDetails> searchByTitlePaged(String title, int limit, int offset);
    
    @Query("SELECT COUNT(*) FROM entries WHERE main_category = :category")
    int getEntriesCountByCategory(String category);
    
    @Query("SELECT COUNT(*) FROM entries WHERE title LIKE '%' || :title || '%'")
    int getSearchResultsCount(String title);
    
    // Filter queries for unique values
    @Query("SELECT DISTINCT sub_category FROM entries WHERE sub_category IS NOT NULL AND sub_category != '' ORDER BY sub_category ASC")
    List<String> getUniqueGenres();
    
    @Query("SELECT DISTINCT country FROM entries WHERE country IS NOT NULL AND country != '' ORDER BY country ASC")
    List<String> getUniqueCountries();
    
    @Query("SELECT DISTINCT year FROM entries WHERE year IS NOT NULL AND year != '' AND year != '0' ORDER BY year DESC")
    List<String> getUniqueYears();
    
    // Filtered pagination queries
    @Transaction
    @Query("SELECT * FROM entries WHERE " +
           "(:genre IS NULL OR sub_category = :genre) AND " +
           "(:country IS NULL OR country = :country) AND " +
           "(:year IS NULL OR year = :year) " +
           "ORDER BY title ASC LIMIT :limit OFFSET :offset")
    List<EntryWithDetails> getEntriesFilteredPaged(String genre, String country, String year, int limit, int offset);
    
    @Query("SELECT COUNT(*) FROM entries WHERE " +
           "(:genre IS NULL OR sub_category = :genre) AND " +
           "(:country IS NULL OR country = :country) AND " +
           "(:year IS NULL OR year = :year)")
    int getEntriesFilteredCount(String genre, String country, String year);

    @Transaction
    @Query("SELECT * FROM entries ORDER BY CAST(rating AS REAL) DESC LIMIT :count")
    List<EntryWithDetails> getTopRatedEntries(int count);

    @Transaction
    @Query("SELECT * FROM entries WHERE id = :id")
    EntryWithDetails getEntryWithDetails(int id);
}