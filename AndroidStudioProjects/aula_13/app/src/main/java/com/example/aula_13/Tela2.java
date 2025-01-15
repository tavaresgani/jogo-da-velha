package com.example.aula_13;

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

public class Tela2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // botao acessar validando se os campos estão preenchidos
        // pegando o botao pelo id
        Button btAcessar = findViewById(R.id.btAcessar);
        btAcessar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etUsuario = findViewById(R.id.etUsuario);
                EditText etSenha = findViewById(R.id.etSenha);

                if(etUsuario.getText().toString().isEmpty() || etSenha.getText().toString().isEmpty()){
                    Toast.makeText(Tela2.this, "Por favor, preencha todos os campos", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}