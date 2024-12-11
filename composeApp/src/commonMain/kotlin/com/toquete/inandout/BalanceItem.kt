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
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BalanceItem(
    transaction: Transaction,
    onDeleteClick: (String) -> Unit,
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
                    transaction.category.name,
                    style = MaterialTheme.typography.caption
                )
                Text(transaction.description)
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    Chip(onClick = {}) {
                        Text(
                            transaction.category.type.type,
                            style = MaterialTheme.typography.overline
                        )
                    }
                    Chip(onClick = {}) {
                        Text(
                            transaction.status.status,
                            style = MaterialTheme.typography.overline
                        )
                    }
                }
            }
        }
        Column(horizontalAlignment = Alignment.End) {
            Text(
                transaction.date,
                style = MaterialTheme.typography.caption
            )
            Text(
                transaction.amount.toString(),
                style = MaterialTheme.typography.body1
            )
        }
        IconButton(onClick = { onDeleteClick(transaction.id) }) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete transaction"
            )
        }
    }
}