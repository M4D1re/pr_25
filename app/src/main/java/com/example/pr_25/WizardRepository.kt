package com.example.pr_25

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import java.util.UUID

class WizardRepository(private val wizardDao: WizardDao) {

    fun getAllWizardsWithDetails(): Flow<List<WizardWithDetails>> =
        wizardDao.getAllWizardsWithDetails()

    suspend fun insertWizard(wizard: Wizard, kindOfMagic: KindOfMagic, magicLevel: MagicLevel) {
        wizardDao.insertWizard(wizard)
        wizardDao.insertMagicKind(kindOfMagic)
        wizardDao.insertMagicLevel(magicLevel)
    }

    suspend fun updateWizard(wizard: Wizard, kindOfMagic: KindOfMagic, magicLevel: MagicLevel) {
        wizardDao.updateWizard(wizard)
        wizardDao.updateMagicKind(kindOfMagic)
        wizardDao.updateMagicLevel(magicLevel)
    }

    suspend fun deleteWizard(wizard: Wizard) {
        val wizardId = wizard.wizardId
        val magicKind = wizardDao.getMagicKind(wizardId)
        val magicLevel = wizardDao.getMagicLevel(wizardId)

        wizardDao.deleteWizard(wizard)
        magicKind?.let { wizardDao.deleteMagicKind(it) }
        magicLevel?.let { wizardDao.deleteMagicLevel(it) }
    }

    suspend fun getWizardDetails(wizardId: String): Triple<Wizard?, KindOfMagic?, MagicLevel?> {
        val wizard = wizardDao.getAllWizards().firstOrNull()?.find { it.wizardId == wizardId }
        val magicKind = wizardDao.getMagicKind(wizardId)
        val magicLevel = wizardDao.getMagicLevel(wizardId)
        return Triple(wizard, magicKind, magicLevel)
    }

    fun generateId(): String = UUID.randomUUID().toString()
}