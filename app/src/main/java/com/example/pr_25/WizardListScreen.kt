package com.example.pr_25

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WizardListScreen(
    viewModel: WizardViewModel
) {
    val wizards by viewModel.wizards.collectAsState()
    val showAddDialog by viewModel.showAddDialog.collectAsState()
    val editingWizard by viewModel.editingWizard.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { viewModel.showAddDialog() }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Wizard")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            Text(
                text = "Wizards Database",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )

            if (wizards.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No wizards found. Add your first wizard!")
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(wizards, key = { it.wizard.wizardId }) { wizard ->
                        WizardCard(
                            wizard = wizard,
                            onEdit = { viewModel.setEditingWizard(wizard) },
                            onDelete = { viewModel.deleteWizard(wizard) }
                        )
                    }
                }
            }
        }
    }

    if (showAddDialog) {
        WizardDialog(
            wizard = editingWizard,
            onDismiss = { viewModel.hideAddDialog() },
            onConfirm = { name, age, house, magicType, specialization, level, experience ->
                if (editingWizard == null) {
                    viewModel.addWizard(name, age, house, magicType, specialization, level, experience)
                } else {
                    viewModel.updateWizard(
                        editingWizard!!.wizard.wizardId,
                        name, age, house, magicType, specialization, level, experience
                    )
                }
                viewModel.hideAddDialog()
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WizardCard(
    wizard: WizardWithDetails,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = wizard.wizard.name,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "House: ${wizard.wizard.house}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "Age: ${wizard.wizard.age}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                Row {
                    IconButton(onClick = onEdit) {
                        Icon(Icons.Default.Edit, contentDescription = "Edit")
                    }
                    IconButton(onClick = onDelete) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete")
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            wizard.kindOfMagic?.let { kindOfMagic ->
                Text(
                    text = "Magic: ${kindOfMagic.magicType}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Specialization: ${kindOfMagic.specialization}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            wizard.magicLevel?.let { magicLevel ->
                Text(
                    text = "Level: ${magicLevel.level}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Experience: ${magicLevel.experience}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}