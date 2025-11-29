package com.example.pr_25

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface WizardDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWizard(wizard: Wizard)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMagicKind(kindOfMagic: KindOfMagic)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMagicLevel(magicLevel: MagicLevel)

    @Update
    suspend fun updateWizard(wizard: Wizard)

    @Update
    suspend fun updateMagicKind(kindOfMagic: KindOfMagic)

    @Update
    suspend fun updateMagicLevel(magicLevel: MagicLevel)

    @Delete
    suspend fun deleteWizard(wizard: Wizard)

    @Delete
    suspend fun deleteMagicKind(kindOfMagic: KindOfMagic)

    @Delete
    suspend fun deleteMagicLevel(magicLevel: MagicLevel)

    @Query("SELECT * FROM wizards")
    fun getAllWizards(): Flow<List<Wizard>>

    @Query("SELECT * FROM kind_of_magic WHERE wizardId = :wizardId")
    suspend fun getMagicKind(wizardId: String): KindOfMagic?

    @Query("SELECT * FROM magic_level WHERE wizardId = :wizardId")
    suspend fun getMagicLevel(wizardId: String): MagicLevel?

    @Query("SELECT * FROM wizards")
    fun getAllWizardsWithDetails(): Flow<List<WizardWithDetails>>
}