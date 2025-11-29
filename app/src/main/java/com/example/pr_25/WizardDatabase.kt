package com.example.pr_25

import androidx.room.*
import android.content.Context

@Database(
    entities = [Wizard::class, KindOfMagic::class, MagicLevel::class],
    version = 1,
    exportSchema = false
)
abstract class WizardDatabase : RoomDatabase() {
    abstract fun wizardDao(): WizardDao

    companion object {
        @Volatile
        private var INSTANCE: WizardDatabase? = null

        fun getDatabase(context: Context): WizardDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WizardDatabase::class.java,
                    "wizard_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}