package com.example.jogodavelha;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TelaJogo extends AppCompatActivity implements View.OnClickListener {
    // TELA JOGO MULTIPLAYER
    private TextView txt_placarJogador1, txt_placarJogador2, txt_statusJogo;
    private Button[] btn = new Button[9];
    private Button btn_reiniciarJogo;
    private boolean jogador;
    private int numJogadas, placarJogador1, placarJogador2;
    private int[] jogadas = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    private int[][] vitorias = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_jogo);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        txt_placarJogador1 = findViewById(R.id.txt_placarJogador);
        txt_placarJogador2 = findViewById(R.id.txt_placarJogador2);
        txt_statusJogo = findViewById(R.id.txt_statusJogoSp);

        btn_reiniciarJogo = findViewById(R.id.btn_reiniciarJogo);

        //funcionalidade dos botoes
        for (int i = 0; i < btn.length; i++) {
            int resId = getResources().getIdentifier("btn_" + i, "id", getPackageName());
            btn[i] = findViewById(resId);
            btn[i].setOnClickListener(this);
        }
        jogador = true;
        placarJogador1 = 0;
        placarJogador2 = 0;
        numJogadas = 1;

        btn_reiniciarJogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placarJogador1 = 0;
                placarJogador2 = 0;
                reiniciaJogo();
                exibePlacar();
            }
        });
    }

    @Override
    public void onClick(View view) {
        //Toast.makeText(this, "Botao pressionado", Toast.LENGTH_SHORT).show();

        //verifica se o botão não "está vazio" antes de executar algum comando
        //garante que jogadas inválidas não aconteça
        if (!((Button) view).getText().toString().equals("")) {
            return;
        }
        int numBtn = Integer.parseInt(view.getResources().getResourceEntryName(view.getId()).substring(4, 5));

        //Alterna entre os jogadores no jogo
        if (jogador) {
            ((Button) view).setText("X");
            jogadas[numBtn] = 1;
        } else {
            ((Button) view).setText("0");
            jogadas[numBtn] = 2;
        }

        if (venceu()) {
            if (jogador) {
                placarJogador1++;
                Toast.makeText(this, "Jogador 1 venceu", Toast.LENGTH_SHORT).show();
            } else {
                placarJogador2++;
                Toast.makeText(this, "Jogador 2  venceu", Toast.LENGTH_SHORT).show();
            }
            exibePlacar();
            reiniciaJogo();

        } else if (numJogadas == 9) {
            Toast.makeText(this, "Deu velha", Toast.LENGTH_SHORT).show();
            reiniciaJogo();
        } else {
            numJogadas++;
            //alterna entre o jogador mudando a vez
            jogador = !jogador;
        }
    }

    private void exibePlacar() {
        txt_placarJogador1.setText("" + placarJogador1);
        txt_placarJogador2.setText("" + placarJogador2);

        if (placarJogador1>placarJogador2){
            txt_statusJogo.setText("Jogador 1 está ganhando");
        } else if (placarJogador2 > placarJogador1) {
            txt_statusJogo.setText("Jogador 2 está ganhando");
        } else{
            txt_statusJogo.setText("O jogo está empatado");
        }
    }

    private void reiniciaJogo(){
        numJogadas = 1;
        jogador = true;
        for(int i = 0; i < btn.length; i++){
            btn[i].setText("");
            jogadas[i] = 0;
        }
    }


    private boolean venceu() {
        //verifica se houve algum vencedor e começa como falso
        boolean resultado = false;
        //um loop para verificar as combinações de vitoria nas jogadas
        for (int[] jogadasefetuadas : vitorias) {
            //verifica se as três posições são de um mesmo jogador e se não estão vazias
            if (jogadas[jogadasefetuadas[0]] == jogadas[jogadasefetuadas[1]] &&
                    jogadas[jogadasefetuadas[1]] == jogadas[jogadasefetuadas[2]] &&
                    jogadas[jogadasefetuadas[0]] != 0) {
                //se for vdd o looping o resultado é true
                resultado = true;
            }
        }
        return resultado;


    }
}



