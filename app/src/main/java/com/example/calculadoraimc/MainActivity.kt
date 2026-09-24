package com.example.calculadoraimc

import android.R.attr.text
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculadoraimc.ui.theme.CalculadoraIMCTheme
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculadoraIMCTheme {
                ImcScreen(
                    modifier = Modifier.padding(32.dp))
                }
            }
        }
    }

@Composable
fun ImcScreen(modifier: Modifier = Modifier) {
    var altura by remember { mutableStateOf("") }
    var peso by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize()
    )
    {
        Box(
            modifier = Modifier.fillMaxSize()
        )
        {
            Column(modifier = Modifier.fillMaxWidth())
            {
                // -- Header --
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                        .background(color = colorResource(R.color.cor_app)),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        modifier = Modifier.size(80.dp)
                            .padding(vertical = 16.dp),
                        painter = painterResource(R.drawable.bmi),
                        contentDescription = "IMC",

                        )

                    Text(
                        text = "Calculadora IMC",
                        fontSize = 24.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }

//                -- Formulário --
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp)
                )
                {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .offset(y = (-30).dp),
                        shape = RoundedCornerShape(18.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        ),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 4.dp
                        )
                    )
                    {

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        )
                        {

                            Text(
                                text = "Seus dados",
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                fontSize = 26.sp,
                                color = colorResource(R.color.cor_app),
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(
                                modifier = Modifier.height(20.dp)
                            )

                            OutlinedTextField(
                                value = altura,
                                onValueChange = {
                                    altura = it
                                },
                                placeholder = {
                                    Text(text = "Altura")
                                },
                                modifier = Modifier
                                    .fillMaxWidth(),
                                singleLine = true,
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Decimal
                                )
                            )

                            Spacer(
                                modifier = Modifier.height(16.dp)
                            )

                            OutlinedTextField(
                                value = peso,
                                onValueChange = {
                                    peso = it
                                },
                                placeholder = {
                                    Text(text = "Peso")
                                },
                                modifier = Modifier
                                    .fillMaxWidth(),
                                singleLine = true,
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Decimal
                                )
                            )

                            Spacer(
                                modifier = Modifier.height(20.dp)
                            )

                            Button(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(52.dp),
                                shape = RoundedCornerShape(30.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor =
                                        colorResource(R.color.cor_app)
                                ),
                            onClick = {
                                }

                            ) {
                                Text( text = "Calcular")
                            }
                        }//Column
                    }//Card
                }//Coluna
            }//Coluna

//                    -- card resultado --



        }//Box
    }//Colum
}//Function

