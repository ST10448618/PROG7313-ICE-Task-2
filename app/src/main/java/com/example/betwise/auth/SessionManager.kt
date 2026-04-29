package com.example.betwise.auth

import android.content.Context

class SessionManager(context: Context) {

    private val prefs = context.getSharedPreferences("auth", Context.MODE_PRIVATE)

    fun saveLogin(username: String) {
        prefs.edit().putString("user", username).apply()
    }

    fun getUser(): String? = prefs.getString("user", null)

    fun logout() {
        prefs.edit().clear().apply()
    }
}