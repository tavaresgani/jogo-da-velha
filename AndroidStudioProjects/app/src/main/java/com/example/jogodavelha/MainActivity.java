package com.example.jogodavelha;

import static com.example.jogodavelha.R.*;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText etNick;
    private ImageView img_avatar1, img_avatar2;
    private Button btn_iniciar, btn_iniciarSp;
    private String avatar_escolhido;

    @SuppressLint("MissingInflatedId")
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

        //busco cada um pelo id
        etNick = findViewById(R.id.etNick);
        img_avatar1 = findViewById(id.img_avatar1);
        img_avatar2 = findViewById(id.img_avatar2);
        btn_iniciar = findViewById(id.btn_iniciar);
        btn_iniciarSp = findViewById(id.btn_iniciarSp);

        //Escolhendo a imagem do avatar1
        img_avatar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                avatar_escolhido = "avatar1";
                img_avatar1.setAlpha(1.0f);
                img_avatar2.setAlpha(0.5f);
            }
        });
        //escolhendo a imagem do avatar2
       img_avatar2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               avatar_escolhido = "avatar2";
               img_avatar1.setAlpha(0.5f);
               img_avatar2.setAlpha(1.0f);
           }
       });

       //Iniciar botão jogo MULTIPLAYER
       btn_iniciar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String nick = etNick.getText().toString();
               if (!nick.isEmpty() && avatar_escolhido != null){
                   Intent intent = new Intent(MainActivity.this, TelaJogo.class);
                   startActivity(intent);
               } else{
                   Toast.makeText(MainActivity.this, "Por favor digite seu nick e escolha um avatar", Toast.LENGTH_LONG).show();
               }
           }
       });

       //Iniciar botão jogo SINGLEPLAYER
       btn_iniciarSp.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String nick = etNick.getText().toString();
               if(!nick.isEmpty() && avatar_escolhido != null){
                   Intent intent2 = new Intent(MainActivity.this, TelaJogoSp.class);
                   startActivity(intent2);
               }
           }
       });


    }
}