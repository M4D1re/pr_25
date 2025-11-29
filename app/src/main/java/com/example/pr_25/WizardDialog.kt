package com.example.pr_25


import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WizardDialog(
    wizard: WizardWithDetails?,
    onDismiss: () -> Unit,
    onConfirm: (String, Int, String, String, String, Int, Int) -> Unit
) {
    var name by remember { mutableStateOf(wizard?.wizard?.name ?: "") }
    var age by remember { mutableStateOf(wizard?.wizard?.age?.toString() ?: "") }
    var house by remember { mutableStateOf(wizard?.wizard?.house ?: "") }
    var magicType by remember { mutableStateOf(wizard?.kindOfMagic?.magicType ?: "") }
    var specialization by remember { mutableStateOf(wizard?.kindOfMagic?.specialization ?: "") }
    var level by remember { mutableStateOf(wizard?.magicLevel?.level?.toString() ?: "1") }
    var experience by remember { mutableStateOf(wizard?.magicLevel?.experience?.toString() ?: "0") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = if (wizard == null) "Add Wizard" else "Edit Wizard") },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Name") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                OutlinedTextField(
                    value = age,
                    onValueChange = { age = it },
                    label = { Text("Age") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                OutlinedTextField(
                    value = house,
                    onValueChange = { house = it },
                    label = { Text("House") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                OutlinedTextField(
                    value = magicType,
                    onValueChange = { magicType = it },
                    label = { Text("Magic Type") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                OutlinedTextField(
                    value = specialization,
                    onValueChange = { specialization = it },
                    label = { Text("Specialization") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                OutlinedTextField(
                    value = level,
                    onValueChange = { level = it },
                    label = { Text("Level") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                OutlinedTextField(
                    value = experience,
                    onValueChange = { experience = it },
                    label = { Text("Experience") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val ageInt = age.toIntOrNull() ?: 0
                    val levelInt = level.toIntOrNull() ?: 1
                    val expInt = experience.toIntOrNull() ?: 0
                    onConfirm(name, ageInt, house, magicType, specialization, levelInt, expInt)
                },
                enabled = name.isNotBlank() && age.isNotBlank() && house.isNotBlank()
            ) {
                Text(if (wizard == null) "Add" else "Update")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}