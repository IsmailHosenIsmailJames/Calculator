package com.ismail.calculator

import android.graphics.drawable.Icon
import android.os.Bundle
import android.provider.CalendarContract.Colors
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ismail.calculator.ui.theme.CalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculatorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        Calculator()
                    }
                }
            }
        }
    }
}

@Composable
fun Calculator() {
    var mathExpression = remember { mutableStateOf<String>("0") }
    var answer = remember { mutableStateOf<String?>(null) }

    val screenHeight = LocalConfiguration.current.screenHeightDp
    val screenWeight = LocalConfiguration.current.screenWidthDp
    val buttonSize: Double = (screenWeight / 4.0) - 10
    val keypadHeight = (screenHeight * 0.7)


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .size(1000.dp, (screenHeight * 0.35).dp)
                .clip(
                    RoundedCornerShape(
                        bottomStart = 16.dp,
                        bottomEnd = 16.dp
                    )
                )
                .background(
                    color = Color.Black.copy(alpha = 0.1f)

                )
                .padding(top = 15.dp, end = 15.dp, start = 0.dp, bottom = 15.dp),

            contentAlignment = Alignment.CenterEnd
        ) {
            Column {
                Text(
                    mathExpression.value,
                    fontSize = if (answer.value == null) 35.sp else 28.sp,
                    fontWeight = FontWeight.SemiBold
                )
                if (answer.value != null) {
                    Text(answer.value!!, fontSize = 35.sp, fontWeight = FontWeight.SemiBold)
                }
            }
        }
        Box(modifier = Modifier.size(60.dp)) {}
        Column(
            modifier = Modifier
                .size(height = keypadHeight.dp, width = screenWeight.dp)
                .padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                GetButtonOfCalculator(text = "AC", onClick = {
                    mathExpression.value = "0"
                    answer.value = null
                }, buttonSize = buttonSize)
                GetButtonOfCalculator(text = "()", onClick = {
                    mathExpression.value = ExpressionModifier().addParentheses(mathExpression.value)
                }, buttonSize = buttonSize)
                GetButtonOfCalculator(text = "%", onClick = {
                    mathExpression.value =
                        ExpressionModifier().addOperator(mathExpression.value, "%")
                }, buttonSize = buttonSize)
                GetButtonOfCalculator(text = "/", onClick = {
                    mathExpression.value =
                        ExpressionModifier().addOperator(mathExpression.value, "/")
                }, buttonSize = buttonSize)
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                GetButtonOfCalculator(
                    text = "7",
                    onClick = {
                        mathExpression.value =
                            ExpressionModifier().addDigit(mathExpression.value, "7")
                    },
                    buttonSize = buttonSize
                )
                GetButtonOfCalculator(text = "8", onClick = {

                    mathExpression.value = ExpressionModifier().addDigit(mathExpression.value, "8")

                }, buttonSize = buttonSize)
                GetButtonOfCalculator(text = "9", onClick = {
                    mathExpression.value = ExpressionModifier().addDigit(mathExpression.value, "9")

                }, buttonSize = buttonSize)
                GetButtonOfCalculator(text = "*", onClick = {
                    mathExpression.value =
                        ExpressionModifier().addOperator(mathExpression.value, "*")
                }, buttonSize = buttonSize)
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                GetButtonOfCalculator(text = "4", onClick = {
                    mathExpression.value = ExpressionModifier().addDigit(mathExpression.value, "4")
                }, buttonSize = buttonSize)
                GetButtonOfCalculator(text = "5", onClick = {
                    mathExpression.value = ExpressionModifier().addDigit(mathExpression.value, "5")
                }, buttonSize = buttonSize)
                GetButtonOfCalculator(text = "6", onClick = {
                    mathExpression.value = ExpressionModifier().addDigit(mathExpression.value, "6")
                }, buttonSize = buttonSize)
                GetButtonOfCalculator(text = "-", onClick = {
                    mathExpression.value =
                        ExpressionModifier().addOperator(mathExpression.value, "-")
                }, buttonSize = buttonSize)
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                GetButtonOfCalculator(text = "1", onClick = {
                    mathExpression.value = ExpressionModifier().addDigit(mathExpression.value, "1")
                }, buttonSize = buttonSize)
                GetButtonOfCalculator(text = "2", onClick = {
                    mathExpression.value = ExpressionModifier().addDigit(mathExpression.value, "2")
                }, buttonSize = buttonSize)
                GetButtonOfCalculator(text = "3", onClick = {
                    mathExpression.value = ExpressionModifier().addDigit(mathExpression.value, "3")
                    println(mathExpression);
                }, buttonSize = buttonSize)
                GetButtonOfCalculator(text = "+", onClick = {
                    mathExpression.value =
                        ExpressionModifier().addOperator(mathExpression.value, "+")
                }, buttonSize = buttonSize)
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                GetButtonOfCalculator(text = "0", onClick = {
                    mathExpression.value = ExpressionModifier().addDigit(mathExpression.value, "0")
                }, buttonSize = buttonSize)
                GetButtonOfCalculator(text = ".", onClick = {
                    mathExpression.value = ExpressionModifier().addDigit(mathExpression.value, ".")
                }, buttonSize = buttonSize)
                GetButtonOfCalculator(text = "back_button", onClick = {
                    mathExpression.value = mathExpression.value.dropLast(1)
                    if (mathExpression.value == "") mathExpression.value = "0"
                }, buttonSize = buttonSize)
                GetButtonOfCalculator(text = "=", onClick = {
                    answer.value = ExpressionCalculator().calculate(mathExpression.value)

                }, buttonSize = buttonSize)
            }
        }
    }


}

@Composable
fun GetButtonOfCalculator(
    text: String,
    onClick: () -> Unit,
    buttonSize: Double
) {
    var color: Color =
        if (text.toIntOrNull() != null) Color.Gray.copy(alpha = 0.1f) else Color.Gray.copy(alpha = 0.2f);
    if (text == "back_button") color = Color.Red.copy(alpha = 0.1f)
    if (text == "AC") color = Color.Red.copy(alpha = 0.5f)


    if (text == "back_button") {
        Box(
            modifier = Modifier
                .size(buttonSize.dp)
                .padding(5.dp)
                .background(color = color, shape = RoundedCornerShape(100.dp))
                .clickable { onClick() }, Alignment.Center
        ) {
            Icon(Icons.AutoMirrored.Filled.KeyboardArrowLeft, contentDescription = null)
        }
    } else {
        Box(
            modifier = Modifier
                .size(buttonSize.dp)
                .padding(5.dp)
                .background(color = color, shape = RoundedCornerShape(100.dp))
                .clickable { onClick() }, Alignment.Center
        ) { Text(text, fontSize = 24.sp, fontWeight = FontWeight.W500) }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CalculatorTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                Calculator()
            }
        }
    }
}

