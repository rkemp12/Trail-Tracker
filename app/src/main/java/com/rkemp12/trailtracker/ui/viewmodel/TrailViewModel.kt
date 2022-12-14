package com.rkemp12.trailtracker.ui.viewmodel

import android.content.ClipData
import android.provider.SyncStateContract.Helpers.insert
import androidx.lifecycle.*
import com.rkemp12.trailtracker.data.TrackerDao
import com.rkemp12.trailtracker.model.Tracker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class TrailViewModel(private val trackerDao: TrackerDao): ViewModel() {

    val fullTracker: LiveData<List<Tracker>> = trackerDao.getTracker().asLiveData()
    fun singleTracker(id: Long): LiveData<Tracker> = trackerDao.getTrackers(id).asLiveData()

    fun addTracker(
        name: String,
        address: String,
        notes: String
    ) {
        val tracker = Tracker(
            name = name,
            address = address,
            notes = notes
        )


        viewModelScope.launch {
            trackerDao.insert(tracker)
        }
    }

    fun updateTracker(
        id: Long,
        name: String,
        address: String,
        notes: String
    ) {
        val tracker = Tracker(
            id = id,
            name = name,
            address = address,
            notes = notes
        )
        viewModelScope.launch(Dispatchers.IO) {
            trackerDao.update(tracker)
        }
    }

    fun deleteTracker(tracker: Tracker) {
        viewModelScope.launch(Dispatchers.IO) {
            trackerDao.delete(tracker)
        }
    }

    fun isValidEntry(name: String, address: String): Boolean {
        return name.isNotBlank() && address.isNotBlank()
    }
}

class TrackerViewModelFactory(private val trackerDao: TrackerDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TrailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TrailViewModel(trackerDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}