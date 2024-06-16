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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cybersleuth.unit_converter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

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

//code

@Composable
fun UnitConverterApp(modifier: Modifier = Modifier) {
    // State variables
    var inputvalue by remember { mutableStateOf("") }
    var outputvalue by remember { mutableStateOf("") }
    var inputunit by remember { mutableStateOf("Meters") }
    var outputunit by remember { mutableStateOf("Meters") }
    var inputExpanded by remember { mutableStateOf(false) }
    var outputExpanded by remember { mutableStateOf(false) }

    // Conversion rates relative to meters
    val conversionRates = mapOf(
        "Centimeters" to 0.01,
        "Meters" to 1.0,
        "Feet" to 0.3048,
        "Millimeters" to 0.001
    )

    // Conversion logic
    fun converter() {
        val inputValueDouble = inputvalue.toDoubleOrNull() ?: 0.0
        val inputRate = conversionRates[inputunit] ?: 1.0
        val outputRate = conversionRates[outputunit] ?: 1.0
        val outputValueDouble = (inputValueDouble * inputRate / outputRate).roundToInt()
        outputvalue = outputValueDouble.toString()
    }

    // UI layout
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Unit Converter", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = modifier.height(16.dp))

        OutlinedTextField(
            value = inputvalue,
            onValueChange = {
                inputvalue = it
                converter()
            },
            label = { Text("Enter a number") }
        )
        Spacer(modifier = modifier.height(16.dp))

        Row {
            // Input Unit Selection
            Box {
                Button(onClick = { inputExpanded = !inputExpanded }) {
                    Text(text = inputunit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Drop Down")
                }
                DropdownMenu(
                    expanded = inputExpanded,
                    onDismissRequest = { inputExpanded = false }
                ) {
                    conversionRates.keys.forEach { unit ->
                        DropdownMenuItem(
                            text = { Text(unit) },onClick = {
                                inputExpanded = false
                                inputunit = unit
                                converter()
                            }
                        )
                    }
                }
            }
            Spacer(modifier = modifier.width(16.dp))

            // Output Unit Selection
            Box {
                Button(onClick = { outputExpanded = !outputExpanded }) {
                    Text(text = outputunit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Drop Down")
                }
                DropdownMenu(
                    expanded = outputExpanded,
                    onDismissRequest = { outputExpanded = false }
                ) {
                    conversionRates.keys.forEach { unit ->
                        DropdownMenuItem(
                            text = { Text(unit) },
                            onClick = {
                                outputExpanded = false
                                outputunit = unit
                                converter()
                            }
                        )
                    }
                }
            }
        }
        Spacer(modifier = modifier.height(16.dp))

        Text(text = "Result: $outputvalue $outputunit")
    }
}

@Preview(showBackground = true)
@Composable
fun UnitconverPreview(modifier: Modifier = Modifier) {
    UnitConverterApp()
}
