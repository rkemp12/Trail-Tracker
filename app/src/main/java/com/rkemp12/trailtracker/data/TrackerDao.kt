package com.rkemp12.trailtracker.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.rkemp12.trailtracker.model.Tracker
import kotlinx.coroutines.flow.Flow

@Dao
interface TrackerDao {
    @Query("SELECT * FROM Tracker")
    fun getTracker(): Flow<List<Tracker>>
    @Query("SELECT * FROM Tracker WHERE id = :id")
    fun getTrackers(id: Long): Flow<Tracker>
    @Insert
    suspend fun insert(tracker: Tracker)
    @Update
    suspend fun update(tracker: Tracker)
    @Delete
    suspend fun delete(tracker: Tracker)
}