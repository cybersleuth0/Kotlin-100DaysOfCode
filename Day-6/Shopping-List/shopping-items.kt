package com.cybersleuth.myshopinglist
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class ShoppingItems(val id: Int, var name: String, var quantity: Int, var isChanging: Boolean = false)

@Composable
fun ShoppingListApp(modifier: Modifier = Modifier) {
    var sItem by remember { mutableStateOf(listOf<ShoppingItems>()) }
    var showDialog by remember { mutableStateOf(false) }
    var newItemName by remember { mutableStateOf("") }
    var newItemQuantity by remember { mutableStateOf("") }

    Column(
        modifier = modifier.padding(16.dp), // Add padding here for the Column
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,// Center children horizontally
    ) {
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF8983ff),
            ),
            onClick = {
                showDialog = true // Show the dialog when button is clicked
            }
        ) {
            Text(text = "Add Item's",fontWeight = FontWeight.Bold, color = Color.White, fontFamily = FontFamily.Serif)
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp), // Add padding to the LazyColumn
            verticalArrangement = Arrangement.spacedBy(8.dp), // Optional: Space between items
        ) {
            items(sItem){
                item ->
                if(item.isChanging){
                    ShoppingList_Editor(item = item,onEditcomplete={
                        editedName, editedQuantity ->
                        sItem = sItem.map {it.copy(isChanging = false)}
                        val editedItems = sItem.find { it.id == item.id }
                        editedItems?.let {
                            it.name = editedName
                            it.quantity = editedQuantity
                        }
                    })
                } else {
                    ShoppingList_Look(
                        items = item, // Assuming 'item' is defined in the scope
                        onEdit = {
                            sItem = sItem.map {
                                it.copy(isChanging = it.id == item.id)
                            }
                        },
                        onDelete = {
                            sItem=sItem-item
                        }
                    )
                }
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = "Add Items", fontFamily = FontFamily.Monospace)},
            text = {
                Column{
                    //This Text field for item name
                    OutlinedTextField(
                        value = newItemName,
                        onValueChange = { newItemName = it },
                        label = { Text("Item Name", fontFamily = FontFamily.Monospace) },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                    )
                    //This Text field for item quantity
                    OutlinedTextField(
                        value = newItemQuantity,
                        onValueChange = { newItemQuantity = it },
                        label = { Text("Quantity", fontFamily = FontFamily.Monospace) },
                        singleLine = true
                    )
                }
            },
            confirmButton = {
                Row (horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)){

                    Button(onClick = {
                        // Handle confirm action
                        if (newItemName.isNotBlank()) {
                            val newItem = ShoppingItems(id = sItem.size + 1, name = newItemName, quantity = newItemQuantity.toInt())
                            sItem = sItem + newItem
                            newItemName = ""
                            newItemQuantity = ""
                            showDialog = false
                        }
                    }) {
                        Text("Confirm")
                        }
                    Button(onClick = {
                        // Handle cancel action
                        showDialog = false
                    }) {
                        Text("Cancel")

                        newItemName=""
                        newItemQuantity=""
                    }
                }
            }
        )
    }
}

@Composable
fun ShoppingList_Look(
    items: ShoppingItems,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
){
    Row(
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth()
            .border(
                border = BorderStroke(2.dp, Color(0xB221B1F3)),
                shape = RoundedCornerShape(20)
            )
            .padding(1.dp) // Adjust the padding to fit the border properly
            .background(Color(0xFFe6eafd), shape = RoundedCornerShape(20.dp)).padding(1.dp), // Adjust the corner radius for the background
        verticalAlignment = Alignment.CenterVertically
    ){
        //Follwing Row for item name and quantity
        Text(text = "${items.name} Qty:${items.quantity}",
            modifier = Modifier.padding(8.dp).weight(1f),
            fontWeight = FontWeight.Bold, fontSize = 15.sp,fontFamily = FontFamily.Monospace
        )

        //Follwing Row for Edit and Delete Button
        Row(
            modifier=Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            //Edit Button Icon
            IconButton(onClick = {onEdit()}) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = null)
            }
            //Delete Button Icon
            IconButton(onClick = {onDelete()}) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = null)
            }
        }
    }
}


@Composable
fun ShoppingList_Editor(item: ShoppingItems,onEditcomplete: (String, Int) -> Unit){
    var editedName by remember { mutableStateOf(item.name) }
    var editedQuantity by remember { mutableStateOf(item.quantity.toString()) }
    var isEditing by remember { mutableStateOf(item.isChanging) }

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly){
        Column {
            //This Text field for item name
            BasicTextField(value = editedName,
                onValueChange = {editedName=it},
                singleLine = true,
                modifier = Modifier.wrapContentWidth().padding(8.dp)
                )
            //This Text field for item quantity
            BasicTextField(value = editedQuantity,
                onValueChange ={editedQuantity=it},
                singleLine = true,
                modifier = Modifier.wrapContentWidth().padding(8.dp)
            )
            Button(onClick = {
                isEditing = false
                onEditcomplete(editedName, editedQuantity.toIntOrNull() ?: 1)
            }) {
                Text("Save")
            }
        }
    }
}
