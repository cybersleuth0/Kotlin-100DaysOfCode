package com.cybersleuth.state_jpcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cybersleuth.state_jpcompose.ui.theme.StateJPCOMPOSETheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            StateJPCOMPOSETheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    counterApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun counterApp(modifier: Modifier = Modifier) {
    
    var count = rememberSaveable{ mutableIntStateOf(0) }

    Column(modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
        ) {
        Text(text = "${count.value}", fontSize = 48.sp,)
        Spacer(modifier = Modifier.height(16.dp))
        Row(){
            //Button for incrementing the counter
            Button(onClick = { count.intValue++ }) {
                Text(text = "Counter +")
            }
            Spacer(modifier = Modifier.width(16.dp))
            //Button for decrementing the counter
            Button(onClick = { count.intValue-- }) {
                Text(text = "Counter -")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CounterPreview(modifier: Modifier = Modifier) {
    StateJPCOMPOSETheme {
        counterApp()
    }
}
