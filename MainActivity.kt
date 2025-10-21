package com.example.uts_batch_1_intannk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import com.example.uts_batch_1_intannk.ui.theme.Utsbatch1intannkTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Utsbatch1intannkTheme {
                var currentScreen by remember { mutableStateOf("list") }
                var editIndex by remember { mutableStateOf<Int?>(null) }

                when (currentScreen) {
                    "list" -> ListContactScreen(
                        onAddClick = {
                            editIndex = null
                            currentScreen = "add"
                        },
                        onEditClick = { index ->
                            editIndex = index
                            currentScreen = "edit"
                        }
                    )

                    "add" -> AddEditContactScreen(
                        contactIndex = null,
                        onSave = { currentScreen = "list" }
                    )

                    "edit" -> AddEditContactScreen(
                        contactIndex = editIndex,
                        onSave = { currentScreen = "list" }
                    )
                }
            }
        }
    }
}
