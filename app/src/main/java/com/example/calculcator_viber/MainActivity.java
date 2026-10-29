package com.example.calculcator_viber;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.calculcator_viber.R;

public class MainActivity extends AppCompatActivity {

    private TextView tvDisplay;
    private TextView tvHistory;

    private String currentInput = "";
    private String previousInput = "";
    private String currentOperator = "";
    private boolean isNewOperation = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDisplay = findViewById(R.id.tvDisplay);
        tvHistory = findViewById(R.id.tvHistory);

        setupButtons();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("CURRENT_INPUT", currentInput);
        outState.putString("PREVIOUS_INPUT", previousInput);
        outState.putString("CURRENT_OPERATOR", currentOperator);
        outState.putBoolean("IS_NEW_OPERATION", isNewOperation);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        currentInput = savedInstanceState.getString("CURRENT_INPUT", "");
        previousInput = savedInstanceState.getString("PREVIOUS_INPUT", "");
        currentOperator = savedInstanceState.getString("CURRENT_OPERATOR", "");
        isNewOperation = savedInstanceState.getBoolean("IS_NEW_OPERATION", true);

        updateDisplay();
        if (!previousInput.isEmpty()) {
            tvHistory.setText(previousInput + " " + currentOperator);
        }
    }

    private void setupButtons() {
        int[] numericButtons = {
                R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
                R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9
        };

        for (int id : numericButtons) {
            findViewById(id).setOnClickListener(v -> {
                Button b = (Button) v;
                appendNumber(b.getText().toString());
            });
        }

        findViewById(R.id.btnDot).setOnClickListener(v -> appendDot());

        findViewById(R.id.btnPlus).setOnClickListener(v -> setOperator("+"));
        findViewById(R.id.btnMinus).setOnClickListener(v -> setOperator("-"));
        findViewById(R.id.btnMultiply).setOnClickListener(v -> setOperator("×"));
        findViewById(R.id.btnDivide).setOnClickListener(v -> setOperator("÷"));

        findViewById(R.id.btnEquals).setOnClickListener(v -> calculateResult());

        setupSpecialButtons();
    }

    private void setupSpecialButtons() {
        findViewById(R.id.btnC).setOnClickListener(v -> clearAll());
        findViewById(R.id.btnCE).setOnClickListener(v -> clearEntry());
        findViewById(R.id.btnDelete).setOnClickListener(v -> deleteLastDigit());

        findViewById(R.id.btnSign).setOnClickListener(v -> toggleSign());
        findViewById(R.id.btnPercent).setOnClickListener(v -> calculateSpecial("percent"));
        findViewById(R.id.btnSqrt).setOnClickListener(v -> calculateSpecial("sqrt"));
        findViewById(R.id.btnSquare).setOnClickListener(v -> calculateSpecial("square"));
        findViewById(R.id.btnFraction).setOnClickListener(v -> calculateSpecial("fraction"));
    }

    private void appendNumber(String num) {
        if (isNewOperation) {
            currentInput = "";
            isNewOperation = false;
        }
        if (currentInput.equals("0") && !num.equals(".")) {
            currentInput = num;
        } else {
            currentInput += num;
        }
        updateDisplay();
    }

    private void appendDot() {
        if (!currentInput.contains(".")) {
            if (currentInput.isEmpty() || isNewOperation) {
                currentInput = "0.";
            } else {
                currentInput += ".";
            }
            isNewOperation = false;
            updateDisplay();
        }
    }

    private void setOperator(String operator) {
        if (!currentInput.isEmpty()) {
            if (!previousInput.isEmpty() && !isNewOperation) {
                calculateResult();
            }
            previousInput = currentInput;
            currentOperator = operator;
            isNewOperation = true;
            tvHistory.setText(previousInput + " " + currentOperator);
        }
    }

    private void calculateResult() {
        if (previousInput.isEmpty() || currentInput.isEmpty() || currentOperator.isEmpty()) return;

        try {
            double num1 = Double.parseDouble(previousInput);
            double num2 = Double.parseDouble(currentInput);
            double result = 0.0;

            switch (currentOperator) {
                case "+": result = num1 + num2; break;
                case "-": result = num1 - num2; break;
                case "×": result = num1 * num2; break;
                case "÷":
                    if (num2 != 0.0) result = num1 / num2;
                    else result = Double.NaN;
                    break;
            }

            String historyText = previousInput + " " + currentOperator + " " + currentInput + " =";
            tvHistory.setText(historyText);

            currentInput = formatResult(result);
            previousInput = "";
            currentOperator = "";
            isNewOperation = true;
            updateDisplay();

        } catch (NumberFormatException e) {
            currentInput = "Error";
            updateDisplay();
        }
    }

    private void calculateSpecial(String type) {
        if (currentInput.isEmpty()) return;
        try {
            double num = Double.parseDouble(currentInput);
            double result = 0.0;
            String historyText = "";

            switch (type) {
                case "percent":
                    result = num / 100.0;
                    historyText = num + " %";
                    break;
                case "sqrt":
                    result = num >= 0 ? Math.sqrt(num) : Double.NaN;
                    historyText = "√(" + num + ")";
                    break;
                case "square":
                    result = num * num;
                    historyText = "sqr(" + num + ")";
                    break;
                case "fraction":
                    result = num != 0.0 ? 1.0 / num : Double.NaN;
                    historyText = "1/(" + num + ")";
                    break;
            }

            tvHistory.setText(historyText);
            currentInput = formatResult(result);
            isNewOperation = true;
            updateDisplay();
        } catch (NumberFormatException e) {
            currentInput = "Error";
            updateDisplay();
        }
    }

    private void deleteLastDigit() {
        if (currentInput.equals("Error")) {
            clearAll();
            return;
        }

        if (!currentInput.isEmpty() && !isNewOperation) {
            currentInput = currentInput.substring(0, currentInput.length() - 1);
            if (currentInput.isEmpty() || currentInput.equals("-")) {
                currentInput = "0";
            }
            updateDisplay();
        }
    }

    private void toggleSign() {
        if (currentInput.equals("Error")) {
            return;
        }

        if (!currentInput.isEmpty() && !currentInput.equals("0")) {
            if (currentInput.startsWith("-")) {
                currentInput = currentInput.substring(1);
            } else {
                currentInput = "-" + currentInput;
            }
            updateDisplay();
        }
    }
    private void clearEntry() {
        currentInput = "0";
        isNewOperation = true;
        updateDisplay();
    }

    private void clearAll() {
        currentInput = "0";
        previousInput = "";
        currentOperator = "";
        isNewOperation = true;
        tvHistory.setText("");
        updateDisplay();
    }

    private void updateDisplay() {
        tvDisplay.setText(currentInput);
    }

    private String formatResult(double result) {
        if (Double.isNaN(result)) return "Error";
        if (result % 1.0 == 0.0) {
            return String.valueOf((long) result);
        }
        return String.valueOf(result);
    }
}