package com.example.todo

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todo.ui.theme.ToDoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    ToDoList()
                }
            }
        }
    }
}

@Composable
fun ToDoList() {
    var todoItems by remember { mutableStateOf(mutableListOf<String>()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Add ToDo Item
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            var newItemText by remember { mutableStateOf("") }
            TextField(
                value = newItemText,
                onValueChange = { newItemText = it },
                label = { Text("Add new item") },
                modifier = Modifier
                    .weight(1f)
            )
            Button(
                onClick = {
                    if (newItemText.isNotBlank()) {
                        todoItems.add(newItemText)
                        newItemText = ""
                    }
                }
            ) {
                Text("Add")
            }
        }

        // Show ToDo Items
        todoItems.forEach { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Text(text = item, modifier = Modifier.weight(1f))
                IconButton(onClick = { todoItems = todoItems.filter { it != item }.toMutableList() }) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "Delete"
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ToDoListPreview() {
    ToDoTheme {
        ToDoList()
    }
}
