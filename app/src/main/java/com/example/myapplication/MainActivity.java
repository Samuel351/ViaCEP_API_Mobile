package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    Button btnBuscarCep;
    EditText txtCep, lblCep, lblLogradouro, lblComplemento, lblBairro, lblCidade, lblEstado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtCep = findViewById(R.id.txtCep);
        btnBuscarCep = findViewById(R.id.btnBuscaCep);
        lblCep = findViewById(R.id.lblCep);
        lblLogradouro = findViewById(R.id.lblLogradouro);
        lblComplemento = findViewById(R.id.lblComplemento);
        lblBairro = findViewById(R.id.lblBairro);
        lblCidade = findViewById(R.id.lblCidade);
        lblEstado = findViewById(R.id.lblEstado);


        btnBuscarCep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // String endereco = txtCep.getText().toString().trim();

                try {
                    //preencher o cep no lblResposta do layout
                    CEP retorno = new HttpService(txtCep.getText().toString().trim()).execute().get();
                    lblCep.setText(retorno.getCep());
                    lblLogradouro.setText(retorno.getLogradouro());
                    lblComplemento.setText(retorno.getComplemento());
                    lblBairro.setText(retorno.getBairro());
                    lblCidade.setText(retorno.getLocalidade());
                    lblEstado.setText(retorno.getUf());

                    if(retorno.getComplemento().isEmpty())
                    {
                        lblComplemento.setVisibility(View.GONE);
                    }

                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

    }
}