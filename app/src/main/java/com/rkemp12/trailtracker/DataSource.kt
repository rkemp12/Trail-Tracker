package com.rkemp12.trailtracker

import com.rkemp12.trailtracker.R
import com.rkemp12.trailtracker.Trail
class Datasource {

    fun loadTrails(): List<Trail> {
        return listOf<Trail>(
            Trail(R.string.trail1),
            Trail(R.string.trail2),
            Trail(R.string.trail3),
            Trail(R.string.trail4),
            Trail(R.string.trail5),
            Trail(R.string.trail6),
            Trail(R.string.trail7),
            Trail(R.string.trail8),
            Trail(R.string.trail9),
            Trail(R.string.trail10),
            Trail(R.string.trail11)
        )
    }
}