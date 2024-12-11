package com.toquete.inandout

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AlertDialog
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.dialog
import kotlin.reflect.KClass

fun NavGraphBuilder.addTransactionScreen(onDismiss: () -> Unit) {
    dialog("addTransaction") {
        AddTransaction(onDismiss)
    }
}

@Composable
fun AddTransaction(onDismiss: () -> Unit) {
    val viewmodel: TransactionViewModel = viewModel(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T {
                return TransactionViewModel() as T
            }
        }
    )
    val state by viewmodel.state.collectAsStateWithLifecycle()

    if (state.isLoading) {
        CircularProgressIndicator()
    } else {
        val inputState = rememberTransactionInputState(state.categories.first())

        AlertDialog(
            onDismissRequest = onDismiss,
            text = {
                DialogContent(
                    categories = state.categories,
                    inputState = inputState
                )
            },
            buttons = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 8.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onDismiss) {
                        Text("Dismiss")
                    }
                    TextButton(
                        onClick = {
                            val transaction = Transaction(
                                description = inputState.description.text,
                                amount = inputState.amount.text.toBigDecimal(),
                                date = inputState.date.text,
                                category = inputState.selectedCategory,
                                status = inputState.selectedStatus
                            )
                            viewmodel.addTransaction(transaction)
                            onDismiss()
                        }
                    ) {
                        Text("Add transaction")
                    }
                }
            }
        )
    }
}

@Composable
fun DialogContent(
    categories: List<Category>,
    inputState: TransactionInputState
) {
    Column(
        modifier = Modifier.wrapContentSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            value = inputState.description,
            onValueChange = { inputState.description = it },
            singleLine = true,
            label = { Text("Description") },
            placeholder = { Text("Description") },
            trailingIcon = {
                if (inputState.isDescriptionEmpty) {
                    IconButton(onClick = { inputState.description = TextFieldValue() }) {
                        Icon(Icons.Default.Clear, contentDescription = "Clear")
                    }
                }
            }
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            value = inputState.amount,
            onValueChange = { inputState.amount = it },
            singleLine = true,
            label = { Text("Amount") },
            placeholder = { Text("0.00") },
            trailingIcon = {
                if (inputState.isAmountEmpty) {
                    IconButton(onClick = { inputState.amount = TextFieldValue() }) {
                        Icon(Icons.Default.Clear, contentDescription = "Clear")
                    }
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            value = inputState.date,
            onValueChange = { inputState.date = it },
            singleLine = true,
            label = { Text("Date") },
            placeholder = { Text("YYYY-MM-DD") },
            trailingIcon = {
                if (inputState.isDateEmpty) {
                    IconButton(onClick = { inputState.date = TextFieldValue() }) {
                        Icon(Icons.Default.Clear, contentDescription = "Clear")
                    }
                }
            }
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .clickable { inputState.isCategoryDropdownExpanded = true }
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(inputState.selectedCategory.name)
                Icon(Icons.Default.ArrowDropDown, contentDescription = null)
            }
            DropdownMenu(
                expanded = inputState.isCategoryDropdownExpanded,
                onDismissRequest = { inputState.isCategoryDropdownExpanded = false }
            ) {
                categories.forEach { category ->
                    DropdownMenuItem(
                        onClick = {
                            inputState.isCategoryDropdownExpanded = false
                            inputState.selectedCategory = category
                        }
                    ) {
                        Text(category.name)
                    }
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .clickable { inputState.isStatusDropdownExpanded = true }
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(inputState.selectedStatus.status)
                Icon(Icons.Default.ArrowDropDown, contentDescription = null)
            }
            DropdownMenu(
                expanded = inputState.isStatusDropdownExpanded,
                onDismissRequest = { inputState.isStatusDropdownExpanded = false }
            ) {
                Status.entries.forEach { status ->
                    DropdownMenuItem(
                        onClick = {
                            inputState.isStatusDropdownExpanded = false
                            inputState.selectedStatus = status
                        }
                    ) {
                        Text(status.status)
                    }
                }
            }
        }
    }
}

class TransactionInputState(
    private val initialCategory: Category
) {
    var description by mutableStateOf(TextFieldValue())
    var amount by mutableStateOf(TextFieldValue())
    var date by mutableStateOf(TextFieldValue())
    var selectedCategory by mutableStateOf(initialCategory)
    var selectedStatus by mutableStateOf(Status.PENDING)
    var isCategoryDropdownExpanded by mutableStateOf(false)
    var isStatusDropdownExpanded by mutableStateOf(false)

    val isDescriptionEmpty: Boolean
        get() = description.text.isEmpty()

    val isAmountEmpty: Boolean
        get() = amount.text.isEmpty()

    val isDateEmpty: Boolean
        get() = date.text.isEmpty()

    companion object {
        val Saver: Saver<TransactionInputState, *> = listSaver(
            save = {
                listOf(
                    it.initialCategory.id,
                    it.initialCategory.name,
                    it.initialCategory.type,
                    it.initialCategory.isRecurrent
                )
            },
            restore = {
                val category = Category(
                    id = it[0] as String,
                    name = it[1] as String,
                    type = it[2] as TransactionType,
                    isRecurrent = it[3] as Boolean
                )
                TransactionInputState(category)
            }
        )
    }
}

@Composable
fun rememberTransactionInputState(category: Category): TransactionInputState {
    return rememberSaveable(saver = TransactionInputState.Saver) {
        TransactionInputState(category)
    }
}