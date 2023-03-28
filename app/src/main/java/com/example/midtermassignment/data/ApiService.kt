package com.example.midterm2assignment.data

import com.example.midterm2assignment.model.User
import retrofit2.http.GET

interface ApiService {

        @GET("/api/?inc=nat,name,email&results=10")
        suspend fun getUsers(): List<User>
}