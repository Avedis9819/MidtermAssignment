
package com.example.midterm2assignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.midterm2assignment.model.User
import com.example.midtermassignment.ui.theme.MidtermAssignmentTheme

class MainActivity : ComponentActivity() {
    val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MidtermAssignmentTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val usersResult = viewModel.users.observeAsState(Result.loading()).value
                    Column {
                        Button(onClick = { viewModel.getUsers() }) {
                            Text(text = "Get Users")
                        }
                }
            }
        }
    }

    @Composable
    fun UsersList(usersResult: kotlin.Result<List<User>>) {
        when (usersResult) {
            is kotlin.Result.Success -> {
                val posts = usersResult.data
                LazyColumn {
                    items(usersResult) { user ->
                        Card(
                            Modifier
                                .padding(8.dp)
                                .fillMaxWidth()
                        ) {
                            Column(
                                Modifier.padding(8.dp)
                            ) {
                                Text(text = post.title, style = MaterialTheme.typography.h5)
                                Text(text = post.body, style = MaterialTheme.typography.body1)
                            }
                        }
                    }
                }
            }
            is kotlin.Result.Error -> {
                Text(text = "Error: ${postsResult.exception.message}")
            }
            else -> {

            }
        }
    }
}
