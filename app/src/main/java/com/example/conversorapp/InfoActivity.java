package com.example.conversorapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.ActionBar;


public class InfoActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView linkTextView = findViewById(R.id.linkTextView);
        linkTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abrir o link desejado
                String url = "https://www.dolarhoje.net.br/dolar-comercial/#:~:text=Aqui%20no%20site%20DolarHoje.Net.Br%20voc%C3%AA%20encontrar%20informa%C3%A7%C3%B5es%20atualizadas,comercial%2Fturismo%20e%20outras%20moedas%20como%20Euro%20e%20Pesos";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });


        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Informações");
        }


    }

}

