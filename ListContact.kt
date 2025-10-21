package com.example.uts_batch_1_intannk

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListContactScreen(
    onAddClick: () -> Unit,
    onEditClick: (Int) -> Unit
) {
    val purple = Color(0xFF7E57C2)

    val contacts = Contact.contactList.filter {
        it.address.trim().split("\\s+".toRegex()).size >= 5
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Dashboard", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = purple)
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddClick,
                containerColor = purple
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add", tint = Color.White)
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(8.dp)
        ) {
            itemsIndexed(contacts) { index, contact ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .pointerInput(Unit) {
                            detectTapGestures(onLongPress = { onEditClick(index) })
                        }
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = contact.name, style = MaterialTheme.typography.titleMedium)
                        Text(text = contact.address, style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewListContactScreen() {
    ListContactScreen(onAddClick = {}, onEditClick = {})
}
