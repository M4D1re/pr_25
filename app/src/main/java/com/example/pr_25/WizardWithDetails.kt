package com.example.pr_25

import androidx.room.Embedded
import androidx.room.Relation

data class WizardWithDetails(
    @Embedded val wizard: Wizard,

    @Relation(
        parentColumn = "wizardId",
        entityColumn = "wizardId"
    )
    val kindOfMagic: KindOfMagic?,

    @Relation(
        parentColumn = "wizardId",
        entityColumn = "wizardId"
    )
    val magicLevel: MagicLevel?
)