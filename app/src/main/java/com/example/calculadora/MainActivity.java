package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import Calculadora.Calculadora;

public class MainActivity extends AppCompatActivity {

    private TextView display;
    private String currentInput = "";
    private Calculadora calculadora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        calculadora = new Calculadora();

        display = findViewById(R.id.calculator_display);

        setNumberButtonListeners();
        setOperatorButtonListeners();
        setSpecialButtonListeners();
    }

    private void setNumberButtonListeners() {
        int[] numberButtons = {R.id.button_0, R.id.button_1, R.id.button_2, R.id.button_3,
                R.id.button_4, R.id.button_5, R.id.button_6, R.id.button_7, R.id.button_8, R.id.button_9, R.id.button_punto};

        for (int id : numberButtons) {
            findViewById(id).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Button button = (Button) view;
                    String value = button.getText().toString();

                    if (value.equals(".")) {
                        if (currentInput.contains(".")) {
                            // Evitar múltiples puntos decimales
                            return;
                        } else if (currentInput.isEmpty()) {
                            currentInput = "0";
                        }
                    }

                    currentInput += value;
                    display.setText(currentInput);
                }
            });
        }
    }

    private void setOperatorButtonListeners() {
        int[] operatorButtons = {
                R.id.button_sumar, R.id.button_restar, R.id.button_multiplicar, R.id.button_dividir,
                R.id.button_raiz
        };

        for (int id : operatorButtons) {
            findViewById(id).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Button button = (Button) view;
                    String operador = button.getText().toString();

                    if (!currentInput.isEmpty()) {
                        try {
                            double operando = Double.parseDouble(currentInput);
                            calculadora.agregarOperando(operando);
                            calculadora.establecerOperador(operador);
                            currentInput = "";
                        } catch (NumberFormatException e) {
                            Toast.makeText(MainActivity.this, "Entrada inválida", Toast.LENGTH_SHORT).show();
                        } catch (IllegalArgumentException e) {
                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        }
    }

    private void setSpecialButtonListeners() {

        findViewById(R.id.button_ac).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculadora.reiniciar();
                currentInput = "";
                display.setText("0");
            }
        });


        findViewById(R.id.button_igual).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!currentInput.isEmpty() || calculadora.getOperandos().size() > 0) {
                    try {
                        if (!currentInput.isEmpty()) {
                            double operando = Double.parseDouble(currentInput);
                            calculadora.agregarOperando(operando);
                        }
                        double resultado = calculadora.calcular();
                        display.setText(String.valueOf(resultado));
                        currentInput = String.valueOf(resultado);
                    } catch (NumberFormatException e) {
                        Toast.makeText(MainActivity.this, "Entrada inválida", Toast.LENGTH_SHORT).show();
                    } catch (IllegalArgumentException | ArithmeticException e) {
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        findViewById(R.id.button_porcentaje).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!currentInput.isEmpty()) {
                    try {
                        double porcentaje = Double.parseDouble(currentInput) / 100;
                        display.setText(String.valueOf(porcentaje));
                        currentInput = String.valueOf(porcentaje);
                    } catch (NumberFormatException e) {
                        Toast.makeText(MainActivity.this, "Entrada inválida", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        findViewById(R.id.button_borrar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!currentInput.isEmpty()) {
                    currentInput = currentInput.substring(0, currentInput.length() - 1);
                    if (currentInput.isEmpty()) {
                        display.setText("0");
                    } else {
                        display.setText(currentInput);
                    }
                }
            }
        });


        findViewById(R.id.button_raiz).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                String operador = button.getText().toString();
                if (!currentInput.isEmpty()) {
                    try {
                        double operando = Double.parseDouble(currentInput);
                        calculadora.agregarOperando(operando);
                        calculadora.establecerOperador(operador);
                        double resultado = calculadora.calcular();
                        display.setText(String.valueOf(resultado));
                        currentInput = String.valueOf(resultado);
                    } catch (NumberFormatException e) {
                        Toast.makeText(MainActivity.this, "Entrada inválida", Toast.LENGTH_SHORT).show();
                    } catch (IllegalArgumentException | ArithmeticException e) {
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }
}
