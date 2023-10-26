package br.ulbra.appjokenpo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.AndroidException;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;


public class MainActivity extends AppCompatActivity {

    EditText txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void selecionadoPedra(View view) {
        this.opcaoSelecionado("pedra");
    }
    public void selecionadoPapel(View view) {
        this.opcaoSelecionado("papel");
    }
    public void selecionadoTesoura(View view) {
        this.opcaoSelecionado("tesoura");
    }
    public void opcaoSelecionado(String opcaoSelecionada) {
        ImageView imageResultado = findViewById(R.id.imgApp);
        TextView txtResult= findViewById(R.id.txtResultado);
        String opcoes[] = {"pedra", "papel", "tesoura"};
        String opcaoApp = opcoes[new Random().nextInt(3)];
        switch (opcaoApp) {
            case "pedra":
                imageResultado.setImageResource(R.drawable.pedra);
                break;
            case "papel":
                imageResultado.setImageResource(R.drawable.papel);
                break;
            case "tesoura":
                imageResultado.setImageResource(R.drawable.tesoura);
                break;
        }
        if ((opcaoApp.equals("tesoura") && opcaoSelecionada.equals("papel")) ||
        (opcaoApp.equals("papel") && opcaoSelecionada.equals("pedra")) ||
        (opcaoApp.equals("pedra") && opcaoSelecionada.equals("tesoura"))) {
            txtResult.setText("Resultado: Você PERDEU... :(");

            pontuacaoApp++;
            atualizarPlacar();

        } else if ((opcaoSelecionada.equals("tesoura") &&
        opcaoApp.equals("papel")) ||
        (opcaoSelecionada.equals("papel") && opcaoApp.equals("pedra"))
||
        (opcaoSelecionada.equals("pedra") &&
        opcaoApp.equals("tesoura"))) {
            txtResult.setText("Resultado: Você GANHOU... ");

            pontuacaoJogador++;
            atualizarPlacar();

        } else {
            txtResult.setText("Resultado: Vocês EMPATARAM... ");
        }
    }


    int pontuacaoApp, pontuacaoJogador;
    public void atualizarPlacar() {

        TextView txtPlacar = findViewById(R.id.txtPlacar);
        TextView txtResultado = findViewById(R.id.txtResultado);

        if(pontuacaoApp >= 2 || pontuacaoJogador >= 2 ){
            if(pontuacaoJogador > pontuacaoApp){
                txtResultado.setText("FIM DE JOGO Você GANHOU!");
                reiniciarJogo(txtPlacar);
            }else {
                txtResultado.setText("FIM DE JOGO Você PERDEU!");
                reiniciarJogo(txtPlacar);
            }
        }
        txtPlacar.setText("Você: " + pontuacaoJogador + " Bot: "+ pontuacaoApp);
    }

    public void reiniciarJogo(View view) {
        pontuacaoJogador = 0;
        pontuacaoApp = 0;
        atualizarPlacar();
        ImageView imageResultado = findViewById(R.id.imgApp);
        imageResultado.setImageDrawable(getDrawable(R.drawable.padrao)); // Limpa a imagem
    }

}

