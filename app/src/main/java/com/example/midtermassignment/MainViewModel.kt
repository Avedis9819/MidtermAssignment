package com.example.midterm2assignment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.midterm2assignment.data.Data
import com.example.midterm2assignment.model.User
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val _user = MutableLiveData<Result<List<User>>>()
    val users: LiveData<Result<List<User>>> = _user

    fun getUsers() {
        viewModelScope.launch {
            try {
                val response = Data().loadUsers()
                _user.postValue(Result.success(response))
            } catch (e: Exception) {
                _user.postValue(Result.error(e))
            }
        }
    }
}

sealed class Result<out T : Any> {
    data class Loading(val message: String = "") : Result<Nothing>()
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()

    companion object {
        fun <T : Any> loading(message: String = ""): Result<T> = Loading(message)
        fun <T : Any> success(data: T): Result<T> = Success(data)
        fun error(exception: Exception): Result<Nothing> = Error(exception)
    }
}