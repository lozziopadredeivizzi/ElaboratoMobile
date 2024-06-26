package com.example.elaboratomobile.data.repositories

import android.graphics.Bitmap
import com.example.elaboratomobile.data.database.Utente
import com.example.elaboratomobile.data.database.UtenteDAO
import kotlinx.coroutines.flow.Flow

class UtenteRepository (private val utenteDAO: UtenteDAO) {

    suspend fun checkLogin(username: String, password: String): Utente? {
        return utenteDAO.checkLogin(username, password)
    }

    suspend fun checkUsername(username: String): Utente?{
        return utenteDAO.checkUsername(username)
    }

    suspend fun addUser(user: Utente){
        return utenteDAO.upsert(user)
    }

    suspend fun fingerPrintAlreadyUsed(): Boolean = utenteDAO.fingerPrintAlreadyUsed()

    suspend fun getUtenteByFingerPrint(): Utente = utenteDAO.getUtenteByFingerPrint()

    suspend fun addBiometricId(username: String) = utenteDAO.addBiometricId(username)

    suspend fun removeBiometricId(username: String) = utenteDAO.removeBiometricId(username)

    suspend fun hasBiometric(username: String) : Boolean = utenteDAO.hasImpronta(username)

    fun getFromUsername(username: String): Flow<Utente?>{
        return utenteDAO.getFromUsername(username)
    }

    fun getBookTotalNumber(username: String) : Flow<Int>{
        return utenteDAO.getBookTotalNumber(username)
    }

    fun editEmail(emial: String, username: String){
        return utenteDAO.editEmail(emial, username)
    }

    fun getCurrentPasswordFromUsername(username: String): String{
        return utenteDAO.getCurrentPasswordFromUsername(username)
    }

    fun editPassword(password: String, username: String){
        return utenteDAO.editPassword(password, username)
    }

    suspend fun updatePfp(immagine: Bitmap, username: String){
        utenteDAO.editPfp(immagine, username)
    }
}