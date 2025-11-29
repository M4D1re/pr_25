package com.example.pr_25

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "kind_of_magic")
data class KindOfMagic(
    @PrimaryKey val magicId: String,
    val wizardId: String,
    val magicType: String,
    val specialization: String
)