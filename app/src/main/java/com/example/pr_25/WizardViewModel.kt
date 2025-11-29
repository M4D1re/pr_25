package com.example.pr_25

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class WizardViewModel(private val repository: WizardRepository) : ViewModel() {

    private val _wizards = MutableStateFlow<List<WizardWithDetails>>(emptyList())
    val wizards: StateFlow<List<WizardWithDetails>> = _wizards.asStateFlow()

    private val _showAddDialog = MutableStateFlow(false)
    val showAddDialog: StateFlow<Boolean> = _showAddDialog.asStateFlow()

    private val _editingWizard = MutableStateFlow<WizardWithDetails?>(null)
    val editingWizard: StateFlow<WizardWithDetails?> = _editingWizard.asStateFlow()

    init {
        loadWizards()
    }

    private fun loadWizards() {
        viewModelScope.launch {
            repository.getAllWizardsWithDetails().collect { wizardsList ->
                _wizards.value = wizardsList
            }
        }
    }

    fun showAddDialog() {
        _showAddDialog.value = true
    }

    fun hideAddDialog() {
        _showAddDialog.value = false
        _editingWizard.value = null
    }

    fun setEditingWizard(wizard: WizardWithDetails?) {
        _editingWizard.value = wizard
        _showAddDialog.value = wizard != null
    }

    fun addWizard(
        name: String,
        age: Int,
        house: String,
        magicType: String,
        specialization: String,
        level: Int,
        experience: Int
    ) {
        viewModelScope.launch {
            val wizardId = repository.generateId()
            val wizard = Wizard(wizardId, name, age, house)
            val kindOfMagic = KindOfMagic(repository.generateId(), wizardId, magicType, specialization)
            val magicLevel = MagicLevel(repository.generateId(), wizardId, level, experience)

            repository.insertWizard(wizard, kindOfMagic, magicLevel)
        }
    }

    fun updateWizard(
        wizardId: String,
        name: String,
        age: Int,
        house: String,
        magicType: String,
        specialization: String,
        level: Int,
        experience: Int
    ) {
        viewModelScope.launch {
            val wizard = Wizard(wizardId, name, age, house)
            val (_, currentKindOfMagic, currentMagicLevel) = repository.getWizardDetails(wizardId)

            val kindOfMagic = currentKindOfMagic?.copy(magicType = magicType, specialization = specialization)
                ?: KindOfMagic(repository.generateId(), wizardId, magicType, specialization)

            val magicLevel = currentMagicLevel?.copy(level = level, experience = experience)
                ?: MagicLevel(repository.generateId(), wizardId, level, experience)

            repository.updateWizard(wizard, kindOfMagic, magicLevel)
        }
    }

    fun deleteWizard(wizard: WizardWithDetails) {
        viewModelScope.launch {
            repository.deleteWizard(wizard.wizard)
        }
    }
}