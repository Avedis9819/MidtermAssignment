package com.example.midterm2assignment.data

import com.example.midterm2assignment.model.User

class Data {
    suspend fun loadUsers(): List<User> {
        return RetroFitHelper.getInstance().create(ApiService::class.java).getUsers()
    }
}