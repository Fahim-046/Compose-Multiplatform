package org.fahimdev.cmpboilerplate.presentation.settings.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cmpboilerplate.composeapp.generated.resources.Res
import cmpboilerplate.composeapp.generated.resources.change_avatar_button_label
import cmpboilerplate.composeapp.generated.resources.profile_information_description
import cmpboilerplate.composeapp.generated.resources.profile_information_title
import org.jetbrains.compose.resources.stringResource

@Composable
fun ProfileInformation() {
    Card(
        modifier = Modifier.fillMaxWidth(), border = BorderStroke(
            width = 1.dp, color = MaterialTheme.colorScheme.outline
        ), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Outlined.Person,
                    contentDescription = "Profile",
                    modifier = Modifier.size(28.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    stringResource(Res.string.profile_information_title),
                    style = MaterialTheme.typography.labelLarge.copy(fontSize = 24.sp)
                )
            }
            Text(
                stringResource(Res.string.profile_information_description),
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row {
                ProfileImage(
                    firstName = "Fahim", lastName = "Fardous", size = 72.dp
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        "Fahim Mohammod Fardous",
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 1
                    )
                    Text(
                        "Fahim.fardous.bs23@gmail.com",
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 1
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                    ActionButton(label = stringResource(Res.string.change_avatar_button_label), onPressed = {}, color = MaterialTheme.colorScheme.background)
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}