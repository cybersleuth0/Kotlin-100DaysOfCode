package com.cybersleuth.unit_converter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cybersleuth.unit_converter.ui.theme.UnitConverterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            UnitConverterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    UnitConverterApp(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun UnitConverterApp(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        //In Column all UI elements are stacked below each other

        //Text is used to display text
        Text(text = "Unit Converter", style = MaterialTheme.typography.headlineLarge)
        //OutlinedTextField is used to make a text field with an outline
        OutlinedTextField(value = "", onValueChange = {}, label = { Text("Enter a number") })
        //Spacer is used to add space between UI elements
        Spacer(modifier = modifier.height(16.dp))

        Row{
            //Box is used to make a container for UI elements
            Box {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Select")
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Drop Down")
                }
                DropdownMenu(expanded = true, onDismissRequest = { /*TODO*/ }) {
                    DropdownMenuItem(text = {Text("Centimeters")}, onClick = { /*TODO*/ })
                    DropdownMenuItem(text = {Text("Meters")}, onClick = { /*TODO*/ })
                    DropdownMenuItem(text = {Text("Feet")}, onClick = { /*TODO*/ })
                    DropdownMenuItem(text = {Text("MiliMeters")}, onClick = { /*TODO*/ })
                }
            }
            Spacer(modifier = modifier.width(16.dp))
            Box {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Select")
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Drop Down")
                }
                DropdownMenu(expanded = true, onDismissRequest = { /*TODO*/ }) {
                    DropdownMenuItem(text = {Text("Centimeters")}, onClick = { /*TODO*/ })
                    DropdownMenuItem(text = {Text("Meters")}, onClick = { /*TODO*/ })
                    DropdownMenuItem(text = {Text("Feet")}, onClick = { /*TODO*/ })
                    DropdownMenuItem(text = {Text("MiliMeters")}, onClick = { /*TODO*/ })
                }
            }
        }
        Text(text = "Result")

    }
}

@Preview(showBackground = true)
@Composable
fun UnitconverPreview(modifier: Modifier = Modifier) {
    UnitConverterApp()
}
