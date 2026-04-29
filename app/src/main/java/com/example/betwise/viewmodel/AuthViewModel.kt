package com.example.betwise.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.betwise.data.dao.UserDao
import com.example.betwise.data.entity.User
import kotlinx.coroutines.launch

class AuthViewModel(private val dao: UserDao) : ViewModel() {

    fun register(user: User, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val exists = dao.getUser(user.username)
            if (exists == null) {
                dao.insert(user)
                onResult(true)
            } else {
                onResult(false)
            }
        }
    }

    fun login(username: String, password: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val user = dao.login(username, password)
            onResult(user != null)
        }
    }
}