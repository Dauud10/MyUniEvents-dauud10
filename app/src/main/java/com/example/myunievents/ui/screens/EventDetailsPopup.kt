package com.example.myunievents.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myunievents.data.Event
import com.example.myunievents.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventDetailsPopup(event: Event, onClose: () -> Unit) {

    ModalBottomSheet(
        onDismissRequest = onClose,
        containerColor = HeaderGreen,
        shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = event.name,
                fontSize = 22.sp,
                color = TextBlack
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text("Date: ${event.date}", fontSize = 16.sp, color = TextBlack)
            Text("Time: ${event.time}", fontSize = 16.sp, color = TextBlack)
            Text("Location: ${event.location}", fontSize = 16.sp, color = TextBlack)

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = onClose,
                colors = ButtonDefaults.buttonColors(containerColor = ButtonRed),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(25.dp)
            ) {
                Text("Close", color = TextBlack)
            }
        }
    }
}
