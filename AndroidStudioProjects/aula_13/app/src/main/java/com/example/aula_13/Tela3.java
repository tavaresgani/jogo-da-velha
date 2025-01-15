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

public class Tela3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btCadastrar = findViewById(R.id.btCadastrar);
        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etUsuarioCadastro = findViewById(R.id.etUsuarioCadastro);
                EditText etSenhaCadastro = findViewById(R.id.etSenhaCadastro);
                EditText etNomeCadastro = findViewById(R.id.etNomeCadastro);
                EditText etData = findViewById(R.id.etData);
                EditText etTelefone = findViewById(R.id.etTelefone);

                if(etUsuarioCadastro.getText().toString().isEmpty() || etSenhaCadastro.getText().toString().isEmpty() ||
                        etNomeCadastro.getText().toString().isEmpty() || etData.getText().toString().isEmpty() ||
                        etTelefone.getText().toString().isEmpty()){
                    Toast.makeText(Tela3.this, "Por favor, preencha todos os campos", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}