package com.example.calc

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {

    private lateinit var display: TextView
    private var currentInput: String = ""
    private var operator: String = ""
    private var firstOperand: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calculatrice)

        display = findViewById(R.id.display)

        val button1: Button = findViewById(R.id.button_1)
        val button2: Button = findViewById(R.id.button_2)
        val button3: Button = findViewById(R.id.button_3)
        val button4: Button = findViewById(R.id.button_4)
        val button5: Button = findViewById(R.id.button_5)
        val button6: Button = findViewById(R.id.button_6)
        val button7: Button = findViewById(R.id.button_7)
        val button8: Button = findViewById(R.id.button_8)
        val button9: Button = findViewById(R.id.button_9)
        val button0: Button = findViewById(R.id.button_0)
        val buttonPlus: Button = findViewById(R.id.button_plus)
        val buttonMoins: Button = findViewById(R.id.button_minus)
        val buttonMultiple: Button = findViewById(R.id.button_multiply)
        val buttonDivise: Button = findViewById(R.id.button_divide)
        val buttonClear: Button = findViewById(R.id.button_clear)
        val buttonEgal: Button = findViewById(R.id.button_equals)
        val buttonDecimal: Button = findViewById(R.id.button_decimal)

        fun updateDisplay() {
            display.text = currentInput
        }

        fun appendToInput(value: String) {
            currentInput += value
            updateDisplay()
        }

        button1.setOnClickListener { appendToInput("1") }
        button2.setOnClickListener { appendToInput("2") }
        button3.setOnClickListener { appendToInput("3") }
        button4.setOnClickListener { appendToInput("4") }
        button5.setOnClickListener { appendToInput("5") }
        button6.setOnClickListener { appendToInput("6") }
        button7.setOnClickListener { appendToInput("7") }
        button8.setOnClickListener { appendToInput("8") }
        button9.setOnClickListener { appendToInput("9") }
        button0.setOnClickListener { appendToInput("0") }

        fun setOperator(op: String) {
            if (currentInput.isNotEmpty()) {
                firstOperand = currentInput.replace(",", ".").toDouble()
                operator = op
                currentInput = ""
                updateDisplay()
            }
        }

        buttonPlus.setOnClickListener { setOperator("+") }
        buttonMoins.setOnClickListener { setOperator("-") }
        buttonMultiple.setOnClickListener { setOperator("*") }
        buttonDivise.setOnClickListener { setOperator("/") }

        buttonClear.setOnClickListener {
            currentInput = ""
            operator = ""
            firstOperand = 0.0
            updateDisplay()
        }

        buttonDecimal.setOnClickListener {
            if (!currentInput.contains(",") && !currentInput.contains(".")) {
                appendToInput(",")
            }
        }

        buttonEgal.setOnClickListener {
            if (currentInput.isNotEmpty()) {
                try {
                    val inputForCalculation = currentInput.replace(",", ".")
                    val secondOperand = inputForCalculation.toDouble()

                    val result = when (operator) {
                        "+" -> firstOperand + secondOperand
                        "-" -> firstOperand - secondOperand
                        "*" -> firstOperand * secondOperand
                        "/" -> if (secondOperand != 0.0) firstOperand / secondOperand else {
                            Toast.makeText(this, "Division par zéro impossible", Toast.LENGTH_SHORT).show()
                            return@setOnClickListener
                        }
                        else -> 0.0
                    }

                    currentInput = result.toString()
                    updateDisplay()
                } catch (e: Exception) {
                    Toast.makeText(this, "Erreur dans le calcul", Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }
            }
        }
    }
}