package com.example.calculadoraimc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
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
                ImcScreen()
            }
        }
    }
}

@Composable
fun ImcScreen(modifier: Modifier = Modifier) {

    //Variavel para o textField
    var altura by remember { mutableStateOf("") }
    var peso by remember { mutableStateOf("") }
                //Card de resultado
    var imc by remember { mutableStateOf(0.0) }
    var categoria by remember { mutableStateOf("Resultado") }
    var corResultado by remember { mutableStateOf(Color(0xFF2FA36B)) }


    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        // -- Header --

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .background(color = colorResource(R.color.cor_app)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .size(80.dp)
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

        // -- Conteúdo abaixo do Header --

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Card de Dados
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
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Seus dados",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontSize = 26.sp,
                        color = colorResource(R.color.cor_app),
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    OutlinedTextField(
                        value = altura,
                        onValueChange = { altura = it },
                        placeholder = { Text(text = "Altura") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Decimal
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = peso,
                        onValueChange = { peso = it },
                        placeholder = { Text(text = "Peso") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Decimal
                        )
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        shape = RoundedCornerShape(30.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(R.color.cor_app)
                        ),
                        onClick = {
                            val alturaNumerica = altura
                                .replace(",", ".")
                                .toDoubleOrNull()

                            val alturaEmMetros = if (alturaNumerica != null) alturaNumerica / 100.0 else null

//                          Converte o peso para número
                            val pesoNumerico = peso
                                .replace(",", ".")
                                .toDoubleOrNull()

//                          Verifica se os valores são válidos e calcula
                            if (alturaEmMetros != null && pesoNumerico != null && alturaEmMetros > 0 && pesoNumerico > 0) {
                                imc = pesoNumerico / (alturaEmMetros * alturaEmMetros)

                                //Cores de acordo com o peso
                                if (imc < 18.5) {
                                    categoria = "Abaixo do peso"
                                    corResultado = Color(0xFFE53935) // Vermelho
                                } else if (imc >= 18.5 && imc < 25) {
                                    categoria = "Peso ideal"
                                    corResultado = Color(0xFF2FA36B) // Verde
                                } else if (imc >= 25 && imc < 30) {
                                    categoria = "Levemente acima do peso"
                                    corResultado = Color(0xFFFF9800) // Laranja
                                } else if (imc >= 30 && imc < 35) {
                                    categoria = "Obesidade grau I"
                                    corResultado = Color(0xFFE53935) // Vermelho
                                } else if (imc >= 35 && imc < 40) {
                                    categoria = "Obesidade grau II"
                                    corResultado = Color(0xFFE53935) // Vermelho
                                } else {
                                    categoria = "Obesidade grau III"
                                    corResultado = Color(0xFFE53935) // Vermelho
                                }
                            }
                        }
                    ) {
                        Text(
                            text = "CALCULAR",
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        shape = RoundedCornerShape(30.dp),
                        onClick = {
                            altura = ""
                            peso = ""
                            imc = 0.0
                            categoria = "Resultado"
                            corResultado = Color(0xFF2FA36B)
                        }
                    ) {
                        Text(
                            text = "LIMPAR",
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            // Card de Resultado
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = (-10).dp),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(
                    containerColor = corResultado
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 5.dp
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 20.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = String.format(Locale.US, "%.1f", imc),
                        fontSize = 36.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.size(30.dp))

                    Text(
                        text = categoria,
                        fontSize = 24.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }//Row
            }//Card
        }//Coluna
    }//Coluna
}//Function