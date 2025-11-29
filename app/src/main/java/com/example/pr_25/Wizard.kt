package com.example.pr_25

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wizards")
data class Wizard(
    @PrimaryKey val wizardId: String,
    val name: String,
    val age: Int,
    val house: String
)