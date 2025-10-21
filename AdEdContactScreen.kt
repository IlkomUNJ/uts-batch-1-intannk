package com.example.uts_batch_1_intannk

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class Contact(
    var name: String,
    var phone: String,
    var email: String,
    var address: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditContactScreen(
    contactList: MutableList<Contact>,  
    contactIndex: Int? = null,
    onSave: () -> Unit
) {
    val purple = Color(0xFF7E57C2)

    val isEditing = contactIndex != null
    val existingContact = if (isEditing) contactList[contactIndex!!] else null

    var name by remember { mutableStateOf(TextFieldValue(existingContact?.name ?: "")) }
    var phone by remember { mutableStateOf(TextFieldValue(existingContact?.phone ?: "")) }
    var email by remember { mutableStateOf(TextFieldValue(existingContact?.email ?: "")) }
    var address by remember { mutableStateOf(TextFieldValue(existingContact?.address ?: "")) }

    var errorMessage by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        if (isEditing) "Edit Contact" else "Add Contact",
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = purple)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text("Phone") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = address,
                onValueChange = { address = it },
                label = { Text("Address (min. 5 words)") },
                modifier = Modifier.fillMaxWidth()
            )

            if (errorMessage.isNotEmpty()) {
                Text(errorMessage, color = Color.Red)
            }

            Button(
                onClick = {
                    val wordCount = address.text.trim().split("\\s+".toRegex()).size
                    if (wordCount < 5) {
                        errorMessage = "Address must be at least 5 words!"
                    } else {
                        if (isEditing) {
                            existingContact?.apply {
                                this.name = name.text
                                this.phone = phone.text
                                this.email = email.text
                                this.address = address.text
                            }
                        } else {
                            contactList.add(
                                Contact(
                                    name.text,
                                    phone.text,
                                    email.text,
                                    address.text
                                )
                            )
                        }
                        onSave()
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = purple),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (isEditing) "Save Changes" else "Add Contact", color = Color.White)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAddEditContactScreen() {
    val sampleList = remember { mutableStateListOf<Contact>() }
    AddEditContactScreen(
        contactList = sampleList,
        contactIndex = null,
        onSave = {}
    )
}
