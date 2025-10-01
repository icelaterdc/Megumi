package com.example.kizzylike

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.dead8309.kizzyrpc.KizzyRPC
import org.dead8309.kizzyrpc.models.Activity
import org.dead8309.kizzyrpc.models.Assets
import org.dead8309.kizzyrpc.models.Timestamps
import org.dead8309.kizzyrpc.models.Metadata

// WARNING: KizzyRPC's example shows using a Discord account token to construct KizzyRPC.
// Using user account tokens may violate Discord Terms of Service. This sample includes a token field
// for demonstration only. Do NOT share your token. Prefer using bot accounts or approved methods where possible.
// The app won't help obtain tokens.
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KizzyLikeApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KizzyLikeApp() {
    var token by remember { mutableStateOf("") }
    var applicationId by remember { mutableStateOf("") }
    var appName by remember { mutableStateOf("") }
    var largeImage by remember { mutableStateOf("") }
    var smallImage by remember { mutableStateOf("") }
    var activeName by remember { mutableStateOf("") }
    var details by remember { mutableStateOf("") }
    var partyEnabled by remember { mutableStateOf(false) }
    var partyMin by remember { mutableStateOf("1") }
    var partyMax by remember { mutableStateOf("4") }
    var statusMessage by remember { mutableStateOf("") }

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Kizzy-like RPC (demo)", style = MaterialTheme.typography.headlineSmall, color = MaterialTheme.colorScheme.onBackground)
            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(value = token, onValueChange = { token = it }, label = { Text("Discord Token (WARNING)") }, singleLine = true)
            Text("Do NOT paste tokens you don't own. Using tokens may violate Discord TOS.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.error)
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(value = applicationId, onValueChange = { applicationId = it }, label = { Text("Application ID (client id)") }, singleLine = true)
            OutlinedTextField(value = appName, onValueChange = { appName = it }, label = { Text("App name (large text)") }, singleLine = true)
            OutlinedTextField(value = largeImage, onValueChange = { largeImage = it }, label = { Text("Large image identifier / URL") }, singleLine = true)
            OutlinedTextField(value = smallImage, onValueChange = { smallImage = it }, label = { Text("Small image identifier / URL") }, singleLine = true)
            OutlinedTextField(value = activeName, onValueChange = { activeName = it }, label = { Text("Active name / state") }, singleLine = true)
            OutlinedTextField(value = details, onValueChange = { details = it }, label = { Text("Details text") }, singleLine = true)

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Party mode")
                Switch(checked = partyEnabled, onCheckedChange = { partyEnabled = it })
                Spacer(modifier = Modifier.width(8.dp))
                if (partyEnabled) {
                    OutlinedTextField(value = partyMin, onValueChange = { partyMin = it }, label = { Text("min") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), modifier = Modifier.width(100.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    OutlinedTextField(value = partyMax, onValueChange = { partyMax = it }, label = { Text("max") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), modifier = Modifier.width(100.dp))
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                val context = LocalContext.current
                Button(onClick = {
                    // Build and send RPC activity in background
                    statusMessage = "Sending..."
                    CoroutineScope(Dispatchers.IO).launch {
                        try {
                            // Note: KizzyRPC constructor in upstream examples takes user token.
                            val rpc = KizzyRPC(token)
                            val activity = Activity(
                                applicationId = applicationId,
                                name = activeName.ifEmpty { "Playing" },
                                details = details,
                                state = activeName,
                                type = 0
                            )
                            activity.assets = Assets(largeImage = largeImage, smallImage = smallImage, largeText = appName, smallText = "")
                            if (partyEnabled) {
                                activity.party = org.dead8309.kizzyrpc.models.Party(id = java.util.UUID.randomUUID().toString(), size = listOf(partyMin.toIntOrNull() ?: 1, partyMax.toIntOrNull() ?: 1))
                            }
                            rpc.setActivity(activity = activity, status = "online", since = System.currentTimeMillis())
                            statusMessage = "Presence applied."
                        } catch (e: Exception) {
                            e.printStackTrace()
                            statusMessage = "Failed: ${'$'}{e.message}"
                        }
                    }
                }) {
                    Icon(Icons.Default.Send, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Set Presence")
                }
            }

            Spacer(modifier = Modifier.height(12.dp))
            Text(statusMessage, style = MaterialTheme.typography.bodyMedium)
        }
    }
}