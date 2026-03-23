package com.example.calculadora

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.calculadora.ui.theme.CalculadoraTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculadoraTheme {
                CalculatorScreen()

            }
        }
    }
}

@Composable
fun CalculatorScreen() {
    var display by remember { mutableStateOf("0") }
    var firstOperand by remember { mutableStateOf<Double?>(null) }
    var operator by remember { mutableStateOf<String?>(null) }
    var waitingForSecond by remember { mutableStateOf(false) }

    val button = listOf(
        listOf("7", "8", "9", "/"),
        listOf("4", "5", "6", "*"),
        listOf("1", "2", "3", "-"),
        listOf("0", "C", "=", "+")
    )

    fun onButtonClick(label: String) {
        when (label) {
            "C" -> {
                display = "0"
                firstOperand = null
                operator = null
                waitingForSecond = false
            }
            in listOf("+", "-", "*", "/") -> {
                firstOperand = display.toDoubleOrNull()
                operator = label
                waitingForSecond = true
            }
            "=" -> {
                val second = display.toDoubleOrNull()
                val first = firstOperand
                if (first != null && second != null && operator != null) {
                    val result = when (operator) {
                        "+" -> first + second
                        "-" -> first - second
                        "*" -> first * second
                        "/" -> if (second != 0.0) first / second else Double.NaN
                        else -> second
                    }
                    display = if (result == result.toLong().toDouble()) {
                        result.toLong().toString()
                    } else {
                        result.toString()
                    }
                    firstOperand = null
                    operator = null
                    waitingForSecond = false
                }
            }
            else -> {
                display = if (waitingForSecond || display == "0") {
                    waitingForSecond = false
                    label
                } else {
                    display + label
                }
            }
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2B2B2B))
            .padding(12.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.6f),
            contentAlignment = Alignment.CenterEnd
        ) {
            Text(
                text = display,
                fontSize = 72.sp,
                color = Color.White,
                fontWeight = FontWeight.Light
            )
        }


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f)
                .background(
                    color = Color(0xFF3A3A3A),
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                button.forEach { row ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        row.forEach { label ->
                            Button(
                                onClick = { onButtonClick(label) },
                                modifier = Modifier
                                    .weight(1f)
                                    .height(160.dp),
                                shape = RoundedCornerShape(14.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF7C6FCD)
                                )
                            ) {
                                Text(
                                    text = label,
                                    fontSize = 28.sp,
                                    color = Color.White,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
    }
}



