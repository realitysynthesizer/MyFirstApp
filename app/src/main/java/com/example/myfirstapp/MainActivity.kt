package com.example.myfirstapp

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
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myfirstapp.ui.theme.MyFirstAppTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyFirstAppTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    UnitConverter()

                }
            }
        }
    }
}

@Composable
fun UnitConverter(){
    var inputvalue by remember { mutableStateOf("")}
    var outputvalue by remember { mutableStateOf("")}

    var inputunit by remember { mutableStateOf("Centimetres")}
    var outputunit by remember { mutableStateOf("Metres")}
    var iExpanded by remember { mutableStateOf(false)}
    var oExpanded by remember { mutableStateOf(false) }
    var conversionfactor by remember { mutableDoubleStateOf(0.01) }
    conversionfactor = when {
        (inputunit == outputunit) -> 1.0
        (inputunit == "Centimetres" && outputunit == "Metres") -> 0.01
        (inputunit == "Centimetres" && outputunit == "Feet") -> 0.0328084
        (inputunit == "Centimetres" && outputunit == "Millimetres") -> 10.0
        (inputunit == "Metres" && outputunit == "Centimetres") -> 100.0
        (inputunit == "Metres" && outputunit == "Feet") -> 3.28084
        (inputunit == "Metres" && outputunit == "Millimetres") -> 1000.0
        (inputunit == "Feet" && outputunit == "Centimetres") -> 30.48
        (inputunit == "Feet" && outputunit == "Metres") -> 0.3048
        (inputunit == "Feet" && outputunit == "Millimetres") -> 304.8
        (inputunit == "Millimetres" && outputunit == "Centimetres") -> 0.1
        (inputunit == "Millimetres" && outputunit == "Metres") -> 0.001
        (inputunit == "Millimetres" && outputunit == "Feet") -> 0.00328084
        else -> 0.0
    }
            outputvalue = ((((inputvalue.toDoubleOrNull()?.times(conversionfactor))?.times(100.0))?.roundToInt())?.div(
                100.0
            )).toString()

            Column (Modifier.fillMaxSize() ,verticalArrangement = Arrangement.Center , horizontalAlignment = Alignment.CenterHorizontally){
                Text(text = "Unit Converter", style = MaterialTheme.typography.displayMedium,  fontFamily = FontFamily.Cursive)
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(value =inputvalue , onValueChange = { inputvalue = it}, label = { Text(
                    text = "Enter Value"
                )})
                Spacer(modifier = Modifier.height(16.dp))
                Row {
                    Box {
                        Button(onClick = {iExpanded=true}) {
                            Text(text = inputunit)
                            Icon(Icons.Default.ArrowDropDown, contentDescription = "" )
                        }
                        DropdownMenu(expanded = iExpanded, onDismissRequest = {iExpanded= !iExpanded}) {
                            DropdownMenuItem(
                                text = { Text(text = "Centimetres") },
                                onClick = {
                                    inputunit="Centimetres"
                                    iExpanded=false
                                }
                            )
                            DropdownMenuItem(
                                text = { Text(text = "Metres") },
                                onClick = {
                                    inputunit="Metres"
                                    iExpanded=false
                                }
                            )
                            DropdownMenuItem(
                                text = { Text(text = "Feet") },
                                onClick = {
                                    inputunit="Feet"
                                    iExpanded=false
                                }
                            )
                            DropdownMenuItem(
                                text = { Text(text = "Millimetres") },
                                onClick = {
                                    inputunit="Millimetres"
                                    iExpanded=false
                                }
                            )

                        }

                    }
                    Spacer(modifier = Modifier.width(16.dp))

                    Box {
                        Button(onClick = {oExpanded=true}) {
                            Text(text = outputunit)
                            Icon(Icons.Default.ArrowDropDown, contentDescription = "" )
                        }
                        DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded=!oExpanded }) {
                            DropdownMenuItem(
                                text = { Text(text = "Centimetres") },
                                onClick = {
                                    outputunit="Centimetres"
                                    oExpanded=false
                                }
                            )
                            DropdownMenuItem(
                                text = { Text(text = "Metres") },
                                onClick = {
                                    outputunit="Metres"
                                    oExpanded=false
                                }
                            )
                            DropdownMenuItem(
                                text = { Text(text = "Feet") },
                                onClick = {
                                    outputunit="Feet"
                                    oExpanded=false
                                }
                            )
                            DropdownMenuItem(
                                text = { Text(text = "Millimetres") },
                                onClick = {
                                    outputunit="Millimetres"
                                    oExpanded=false
                                }
                            )

                        }

                    }

                }
                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Result:")
                if (outputvalue=="null") Text(text = "")
                else Text(text = "$outputvalue $outputunit", style = MaterialTheme.typography.headlineMedium)



            }
}




@Preview (showBackground = true)
@Composable
fun Preview(){
    UnitConverter()
}
