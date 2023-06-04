package com.example.conversorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.text.DecimalFormat;
import com.google.android.material.snackbar.Snackbar;
import com.example.conversorapp.InfoActivity;


public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private static final String PREF_LAST_VALUE = "last_value";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText real = findViewById(R.id.editTex);
        TextView dollar = findViewById(R.id.editText);
        Button result = findViewById(R.id.convert);

        View rootView = findViewById(android.R.id.content); // Layout raiz da Activity

        // Inicializar SharedPreferences
        sharedPreferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);

        // Recuperar o último valor inserido, caso exista
        float lastValue = sharedPreferences.getFloat(PREF_LAST_VALUE, 0);
        real.setText(String.valueOf(lastValue));

        Button openActivityButton = findViewById(R.id.openActivityButton);
        openActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String resultadoConversao = dollar.getText().toString(); // Obtém o resultado da conversão
            }
        });

        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String realStr = real.getText().toString();
                    float realConvert = Float.parseFloat(realStr);
                    double converted = realConvert / 4.96;
                    DecimalFormat decimalFormat = new DecimalFormat("0.000");
                    String formattedResult = decimalFormat.format(converted);
                    dollar.setText("Dólar = " + formattedResult);

                    // Armazenar o último valor inserido
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putFloat(PREF_LAST_VALUE, realConvert);
                    editor.apply();

                    // Esconder o teclado virtual
                    InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                } catch (Exception e) {
                    e.printStackTrace();
                    // Exibir o Snackbar com a mensagem de erro
                    Snackbar snackbar = Snackbar.make(rootView, "Erro na conversão", Snackbar.LENGTH_SHORT);
                    snackbar.setAnchorView(view);
                    snackbar.show();
                }

                Button openActivityButton = findViewById(R.id.openActivityButton);
                openActivityButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, InfoActivity.class);
                        startActivity(intent);
                    }
                });

            }
        });
    }
}
