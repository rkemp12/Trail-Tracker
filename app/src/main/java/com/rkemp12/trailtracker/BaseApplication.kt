package com.rkemp12.trailtracker

import android.app.Application
import com.rkemp12.trailtracker.data.TrackerDatabase

class BaseApplication : Application() {
    val database: TrackerDatabase by lazy { TrackerDatabase.getDatabase(this) }

}