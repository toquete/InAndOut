package com.toquete.inandout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Chip
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BalanceItem(
    item: Item,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                modifier = Modifier
                    .background(color = Color.Red, shape = CircleShape)
                    .padding(4.dp),
                imageVector = Icons.Default.Done,
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    item.category,
                    style = MaterialTheme.typography.caption
                )
                Text(item.description)
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    Chip(onClick = {}) {
                        Text(
                            item.type,
                            style = MaterialTheme.typography.overline
                        )
                    }
                    Chip(onClick = {}) {
                        Text(
                            item.status,
                            style = MaterialTheme.typography.overline
                        )
                    }
                }
            }
        }
        Text(item.amount.toString())
    }
}