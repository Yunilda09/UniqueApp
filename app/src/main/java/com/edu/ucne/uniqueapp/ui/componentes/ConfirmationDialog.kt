package com.edu.ucne.uniqueapp.ui.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.edu.ucne.uniqueapp.ui.theme.RosaClaro

@Composable
fun ConfirmationDialog(onDismiss: () -> Unit, onConfirmClick: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .background(color = RosaClaro, shape = RoundedCornerShape(20.dp))
        ) {
            Spacer(Modifier.height(15.dp))
            Text(
                "¿Deseas cancelar esta cita?",
                style = MaterialTheme.typography.bodyLarge,
                color = Color(0xFFF08080)
            )


            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            ) {
                Button(
                    onClick = {
                        onConfirmClick()
                        onDismiss()
                    }, colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFC3ECDE),
                        contentColor = Color(0xFF51BC98)
                    )
                ) {
                    Text(text = "Si")
                }

                Button(
                    onClick = onDismiss, colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFEDEE2),
                        contentColor = Color(0xFFF08080)
                    )
                ) {
                    Text(text = "No")
                }
            }
        }
    }
}