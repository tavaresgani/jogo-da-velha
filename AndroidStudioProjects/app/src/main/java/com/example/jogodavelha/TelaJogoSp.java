package com.example.jogodavelha;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Random;

public class TelaJogoSp extends AppCompatActivity implements View.OnClickListener {
    //TELA JOGO SINGLEPLAYER (SP)

    //Declarando as variaveis
    TextView txt_placarJogador, txt_placarMaquina, txt_statusJogoSp;
    private Button[] btnSp = new Button[9];
    private Button btn_reiniciarJogo2;
    private boolean jogadorSp;
    private int numJogadasSp, placarJogador, placarMaquina;
    private int[] jogadasSp = {0,0,0,0,0,0,0,0,0}; //vetor de jogadas vazias

    //posições de vitorias
    private int [][] vitoriasSp = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_jogo_sp);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

       txt_placarJogador = findViewById(R.id.txt_placarJogador);
       txt_placarMaquina = findViewById(R.id.txt_placarMaquina);
       txt_statusJogoSp = findViewById(R.id.txt_statusJogoSp);
       btn_reiniciarJogo2 = findViewById(R.id.btn_reiniciarJogo2);


       //funcionalidade dos botoes
        for (int i = 0; i < btnSp.length; i++){
            int resId = getResources().getIdentifier("btnSp_" + i, "id", getPackageName());
            btnSp[i] = findViewById(resId);
            btnSp[i].setOnClickListener(this);
        }

        btn_reiniciarJogo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placarJogador = 0;
                placarMaquina = 0;
                reiniciaJogoSp();
                exibePlacarSp();
            }
        });

        //inicia a rodada com o jogador humano
        reiniciaJogoSp();
    }
    @Override
    public void onClick(View view) {
       // Toast.makeText(this, "Botao pressionado", Toast.LENGTH_SHORT).show();
        if (!((Button) view).getText().toString().equals("")) {
            return;
        }
        String resName = view.getResources().getResourceEntryName(view.getId());
        int numBtnSp = Integer.parseInt(resName.substring(resName.lastIndexOf("_") + 1));

        //int numBtnSp = Integer.parseInt(view.getResources().getResourceEntryName(view.getId()).substring(4,5));

        //Alterna entre o jogador e a IA
        if (jogadorSp){
            //verifica quando o jogador seleciona um botão coloca o X e incrementa o vetor de jogadas
            ((Button) view).setText("X");
            jogadasSp[numBtnSp] = 1;
            numJogadasSp++;

        if (venceuSp()){
            placarJogador++;
            Toast.makeText(this, "O Jogador venceu!", Toast.LENGTH_SHORT).show();
            exibePlacarSp();
            reiniciaJogoSp();
            return;
        } else if (numJogadasSp == 9) {
            Toast.makeText(this, "Deu velha!!!", Toast.LENGTH_SHORT).show();
            reiniciaJogoSp();
            return;
        } else {
            //alterna para a vez da máquina
            jogadorSp = false;
            maquinaJoga();
            }
        }
    }

    //verifica se há um vencedor no jogo da velha
    private boolean venceuSp() {
        boolean resultadoSp = false;
        //Esse for percorre cada posição possível de vitoria e verifica se há uma linha, coluna ou diagonal preenchida para declarar um vencedor
        for (int[] jogadas_efetuadasSp : vitoriasSp){
            if (jogadasSp[jogadas_efetuadasSp[0]] == jogadasSp[jogadas_efetuadasSp[1]] &&
                jogadasSp[jogadas_efetuadasSp[1]] == jogadasSp[jogadas_efetuadasSp[2]] &&
                        jogadasSp[jogadas_efetuadasSp[0]] != 0){ //verifica se a posição não está vazia
                    resultadoSp = true;
            }
        }
        return resultadoSp;
    }
    private void exibePlacarSp() {
        txt_placarJogador.setText("" + placarJogador);
        txt_placarMaquina.setText("" + placarMaquina);
        if(placarJogador > placarMaquina){
            txt_statusJogoSp.setText("Jogador está ganhando!");
        } else if (placarMaquina > placarJogador) {
            txt_statusJogoSp.setText("A máquina está ganhando!");
        } else{
            txt_statusJogoSp.setText("O jogo está empatado");
        }
    }
    private void reiniciaJogoSp() {
        //reinicia o contador de jogadas, definido como 1 que conta após o primeiro movimento
        numJogadasSp = 0;
        //define o jogador humano será o proximo a jogar
        jogadorSp = true;
        //acessa todos os botoes e remove as marcações antigas
        for (int i = 0; i < btnSp.length; i++){
            btnSp[i].setText("");
            //indica que as celulas estao vazias
            jogadasSp[i] = 0;
        }
    }
    private void maquinaJoga() {
        //Um array com as celulas vazias
        ArrayList<Integer> celulasVazias = new ArrayList<>();
        //um for que verifica todas as celulas vazias e coloca dentro do array
        for(int i = 0; i < jogadasSp.length; i++){
            if (jogadasSp[i] == 0){
                celulasVazias.add(i);
            }
        }
        //Verifica se o array está disponivel para fazer uma jogada
        if (!celulasVazias.isEmpty()){
            //random é utilizado para pegar um numero aleatorio e a IA escolher uma celula vazia
            Random rand = new Random();
            //Seleciona uma celula aleatoriamente para a IA fazer a jogada
            int index = celulasVazias.get(rand.nextInt(celulasVazias.size()));
            //representa a jogada da IA
            jogadasSp[index] = 2;
            //Coloca O na posição e incrementa o numero de jogadas
            btnSp[index].setText("O");
            numJogadasSp++;

            if (venceuSp()){
                placarMaquina++;
                Toast.makeText(this, "A Máquina venceu!!", Toast.LENGTH_SHORT).show();
                exibePlacarSp();
                reiniciaJogoSp();
                return;
            } else if (numJogadasSp == 9) {
                Toast.makeText(this, "Deu velha!!", Toast.LENGTH_SHORT).show();
                reiniciaJogoSp();
                return;
            } else { //alterna para a vez do jogador
                jogadorSp = true;
            }

        }

    }
}