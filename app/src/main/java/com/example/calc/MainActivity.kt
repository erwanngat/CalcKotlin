package com.example.calc

import android.os.Bundle
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.example.calc.ui.theme.CalcTheme

class MainActivity : ComponentActivity() {

    private lateinit var resultText: EditText
    private var currentInput: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calculatrice)
    }
}

@Composable
fun CalculatorView() {
    AndroidView(
        factory = { context ->
            val layoutInflater = android.view.LayoutInflater.from(context)
            layoutInflater.inflate(R.layout.calculatrice, null)
        },
        modifier = Modifier.fillMaxSize()
    )
}

@Preview(showBackground = true)
@Composable
fun CalculatorPreview() {
    CalcTheme {
        CalculatorView()
    }
}