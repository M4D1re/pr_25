package com.example.pr_25

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.room.Room
import com.example.pr_25.ui.theme.WizardAppTheme



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = WizardDatabase.getDatabase(this)
        val repository = WizardRepository(database.wizardDao())
        val viewModel = WizardViewModel(repository)

        setContent {
            WizardAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WizardListScreen(viewModel = viewModel)
                }
            }
        }
    }
}