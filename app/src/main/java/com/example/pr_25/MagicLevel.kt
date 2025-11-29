package com.example.pr_25

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "magic_level")
data class MagicLevel(
    @PrimaryKey val levelId: String,
    val wizardId: String,
    val level: Int,
    val experience: Int
)