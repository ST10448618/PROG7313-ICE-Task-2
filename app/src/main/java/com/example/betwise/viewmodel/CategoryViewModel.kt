package com.example.betwise.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.betwise.data.dao.CategoryDao
import com.example.betwise.data.entity.Category
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CategoryViewModel(private val dao: CategoryDao) : ViewModel() {

    private val _categories = MutableStateFlow<List<Category>>(emptyList())
    val categories: StateFlow<List<Category>> = _categories

    fun loadCategories() {
        viewModelScope.launch {
            _categories.value = dao.getAll()
        }
    }

    fun addCategory(category: Category) {
        viewModelScope.launch {
            dao.insert(category)
            loadCategories()
        }
    }

    fun insertDefaults() {
        viewModelScope.launch {
            if (dao.getAll().isEmpty()) {
                dao.insert(Category(name = "Slots", description = "Slot machines", imageUri = null))
                dao.insert(Category(name = "Poker", description = "Card game", imageUri = null))
                dao.insert(Category(name = "Roulette", description = "Wheel game", imageUri = null))
                dao.insert(Category(name = "Blackjack", description = "21 game", imageUri = null))
                dao.insert(Category(name = "Lucky Wheel", description = "Spin game", imageUri = null))
                loadCategories()
            }
        }
    }
}