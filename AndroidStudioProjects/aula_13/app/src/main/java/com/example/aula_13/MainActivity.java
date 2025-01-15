package com.example.aula_13;

import static com.example.aula_13.R.id.btAcessar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

        // encontrar pelo id do botao
        Button btLogin = findViewById(R.id.btLogin);
        //ouvinte do clique do botão Login
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // criar uma intent para iniciar a tela2
                Intent it = new Intent(MainActivity.this, Tela2.class);
                startActivity(it);
            }
        });

        // Botão Cadastro
        Button btCadastro = findViewById(R.id.btCadastro);
        btCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it2 = new Intent(MainActivity.this, Tela3.class);
                startActivity(it2);
            }
        });

        //Botão Sobre
        Button btSobre = findViewById(R.id.btSobre);
        btSobre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it3 = new Intent(MainActivity.this, Tela4.class);
                startActivity(it3);
            }
        });

    }


    }