package com.example.calculadora

import android.R.attr.fontWeight
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.tooling.preview.Preview
import com.example.calculadora.ui.theme.CalculadoraTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculadoraTheme {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF2B2B2B))
                    .padding(12.dp)
                )
                {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(160.dp)
                            .weight(1.5f),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Text(
                            text = "0",
                            fontSize = 72.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Light
                        )
                    }


                    val buttons = listOf(
                        listOf("7", "8", "9", "/"),
                        listOf("4", "5", "6", "*"),
                        listOf("1", "2", "3", "-"),
                        listOf("0", "C", "=", "+")
                    )

                    buttons.forEach { row ->
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 10.dp)
                            .weight(1f)
                        ) {
                            row.forEach { label ->
                                Button(
                                    onClick = {},
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(4.dp)
                                        .fillMaxHeight(),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(
                                            0xFF7B5EA7
                                        )
                                    ),
                                    shape = RoundedCornerShape(30.dp)
                                ) {
                                    Text(
                                        label,
                                        fontSize = 24.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

