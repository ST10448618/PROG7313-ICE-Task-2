package com.example.betwise.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.betwise.data.dao.SessionDao
import com.example.betwise.data.entity.Session
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SessionViewModel(private val dao: SessionDao) : ViewModel() {

    private val _sessions = MutableStateFlow<List<Session>>(emptyList())
    val sessions: StateFlow<List<Session>> = _sessions

    fun loadSessions(categoryId: Int) {
        viewModelScope.launch {
            _sessions.value = dao.getByCategory(categoryId)
        }
    }

    fun addSession(session: Session) {
        viewModelScope.launch {
            dao.insert(session)
            loadSessions(session.categoryId)
        }
    }
}