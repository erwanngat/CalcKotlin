package com.example.calc

import android.annotation.SuppressLint
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

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calculatrice)

        display = findViewById(R.id.display)

        val button1: Button = findViewById(R.id.button1)
        val button2: Button = findViewById(R.id.button2)
        val button3: Button = findViewById(R.id.button3)
        val button4: Button = findViewById(R.id.button4)
        val button5: Button = findViewById(R.id.button5)
        val button6: Button = findViewById(R.id.button6)
        val button7: Button = findViewById(R.id.button7)
        val button8: Button = findViewById(R.id.button8)
        val button9: Button = findViewById(R.id.button9)
        val button0: Button = findViewById(R.id.button0)
        val buttonAdd: Button = findViewById(R.id.buttonAdd)
        val buttonMinus: Button = findViewById(R.id.buttonMinus)
        val buttonMultiply: Button = findViewById(R.id.buttonMultiply)
        val buttonDivide: Button = findViewById(R.id.buttonDivide)
        val buttonClear: Button = findViewById(R.id.buttonClear)
        val buttonEquals: Button = findViewById(R.id.buttonEquals)
        val buttonDecimal: Button = findViewById(R.id.buttonDecimal)

        fun updateDisplay() {
            display.text = currentInput
        }

        fun addToInput(value: String) {
            currentInput += value
            updateDisplay()
        }

        button1.setOnClickListener { addToInput("1") }
        button2.setOnClickListener { addToInput("2") }
        button3.setOnClickListener { addToInput("3") }
        button4.setOnClickListener { addToInput("4") }
        button5.setOnClickListener { addToInput("5") }
        button6.setOnClickListener { addToInput("6") }
        button7.setOnClickListener { addToInput("7") }
        button8.setOnClickListener { addToInput("8") }
        button9.setOnClickListener { addToInput("9") }
        button0.setOnClickListener { addToInput("0") }

        fun setOperator(op: String) {
            if (currentInput.isNotEmpty()) {
                firstOperand = currentInput.replace(",", ".").toDouble()
                operator = op
                currentInput = ""
                updateDisplay()
            }
        }

        buttonAdd.setOnClickListener { setOperator("+") }
        buttonMinus.setOnClickListener { setOperator("-") }
        buttonMultiply.setOnClickListener { setOperator("*") }
        buttonDivide.setOnClickListener { setOperator("/") }

        buttonClear.setOnClickListener {
            currentInput = ""
            operator = ""
            firstOperand = 0.0
            updateDisplay()
        }

        buttonDecimal.setOnClickListener {
            if (!currentInput.contains(",") && !currentInput.contains(".")) {
                addToInput(",")
            }
        }

        buttonEquals.setOnClickListener {
            if (currentInput.isNotEmpty()) {
                try {
                    val secondOperand = currentInput.replace(",", ".").toDouble()

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