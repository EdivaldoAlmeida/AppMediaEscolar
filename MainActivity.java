package androidnovo.curso.aulaappmediaescolar;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnCalcular;
    EditText editMateria;
    EditText editnotaProva;
    EditText editnotaTrabalho;
    TextView txtResultado;
    TextView txtSitacaoFinal;

    double notaProva;
    double notaTrabalho;
    double media;

    String resultado;

    boolean dadosValidados = true;  //Testa se os dados estão corretos

    void limpaCampos(){
        txtResultado.setText("");
        txtSitacaoFinal.setText("");
        }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Lincando as variáveis com os ids do formulário.
        editMateria = findViewById(R.id.editMateria);
        editnotaProva = findViewById(R.id.editNotaProva);
        editnotaTrabalho = findViewById(R.id.editNotaTrabalho);
        txtResultado = findViewById(R.id.txtResultado);
        txtSitacaoFinal = findViewById(R.id.txtSituacaoFinal);

        btnCalcular = findViewById(R.id.btnCalcular);


        //Programando o botão calcular
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Capturando os erros
                try{

                    //Testando se os campos são positivos

                    if(editnotaProva.getText().toString().length()>0) {
                        //Aqui é necessário fazer um parse de String p/ Double.
                        notaProva = Double.parseDouble(editnotaProva.getText().toString());
                        dadosValidados = true;
                    }else {
                        editnotaProva.setError("*");
                        editnotaProva.requestFocus();
                        dadosValidados = false;
                        limpaCampos();
                    }

                    if(editnotaTrabalho.getText().toString().length()>0) {
                        notaTrabalho = Double.parseDouble(editnotaTrabalho.getText().toString());
                        dadosValidados = true;

                    }else {
                        editnotaTrabalho.setError("*");
                        editnotaTrabalho.requestFocus();
                        dadosValidados = false;
                        limpaCampos();
                        }

                   if(editMateria.getText().toString().length()==0){
                        editMateria.setError("*");
                        editMateria.requestFocus();
                        dadosValidados = false;
                        limpaCampos();
                    }

                // Só executa após validação, ou seja, se dadosValidades for true

                if(dadosValidados) {
                    media = (notaProva + notaTrabalho) / 2; //calculando a média
                    txtResultado.setText(String.valueOf(media));//atenção ao cast de Double p/ String

                    if (media >= 7) txtSitacaoFinal.setText("Aprovado");
                    else txtSitacaoFinal.setText("Reprovado");

                    }

                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "informe todas as notas...", Toast.LENGTH_LONG).show();
                }
            }
        });



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "App Média Escolar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_sair) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
