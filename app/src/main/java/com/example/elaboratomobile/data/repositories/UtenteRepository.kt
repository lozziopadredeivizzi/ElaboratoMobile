package com.example.elaboratomobile.data.repositories

import com.example.elaboratomobile.data.database.Utente
import com.example.elaboratomobile.data.database.UtenteDAO

class UtenteRepository (private val utenteDAO: UtenteDAO) {

    suspend fun checkLogin(username: String, password: String): Utente? {
        return utenteDAO.checkLogin(username, password)
    }
}