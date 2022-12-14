package com.rkemp12.trailtracker.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Tracker (
    @PrimaryKey val id: Long = 0,
    val name: String,
    val address: String,
    val notes: String?
)