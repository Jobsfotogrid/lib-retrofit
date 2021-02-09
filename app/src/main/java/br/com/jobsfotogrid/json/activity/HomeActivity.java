package br.com.jobsfotogrid.json.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import br.com.jobsfotogrid.json.R;
import br.com.jobsfotogrid.json.api.CEPService;
import br.com.jobsfotogrid.json.api.DataService;
import br.com.jobsfotogrid.json.model.CEP;
import br.com.jobsfotogrid.json.model.Foto;
import br.com.jobsfotogrid.json.model.Postagem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends AppCompatActivity {

    private Button   exibirContacao;
    private TextView exibirValorCotacao;
    private Retrofit retrofit;
    private List<Foto> listaFotos = new ArrayList<>();
    private  DataService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_home);
        inicializarComponentes();

        retrofit = new Retrofit.Builder()
            ///.baseUrl("https:viacep.com.br/ws/")
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
        service = retrofit.create(DataService.class);

        exibirContacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removerPostagem();
            }
        });
    }

    private void removerPostagem(){
        Call<Void> call = service.removerPostagem(2);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if( response.isSuccessful() ){
                    exibirValorCotacao.setText("Status: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }


    private void atualizarPostagem() {
        Postagem postagem = new Postagem("1234", null, "Corpo postagem");
        Call<Postagem> call = service.atualizarPostagem(2, postagem);
        call.enqueue(new Callback<Postagem>() {
            @Override
            public void onResponse(Call<Postagem> call, Response<Postagem> response) {
                if( response.isSuccessful() ){
                    Postagem postagemResposta = response.body();
                    exibirValorCotacao.setText(
                            "Status: " + response.code() +
                            "id: " + postagemResposta.getId() +
                            "userId: " + postagemResposta.getUserId() +
                            "titulo " + postagemResposta.getTitle() +
                            "body" + postagemResposta.getBody()
                    );
                }
            }

            @Override
            public void onFailure(Call<Postagem> call, Throwable t) {

            }
        });
    }

    private void salvarPostagem(){
        Call<Postagem> call = service.salvarPostagem("1234", "Titulo postagem", "Corpo postagem");

        call.enqueue(new Callback<Postagem>() {
            @Override
            public void onResponse(Call<Postagem> call, Response<Postagem> response) {
                if( response.isSuccessful() ){
                    Postagem postagemResposta = response.body();
                    exibirValorCotacao.setText(
                            "Código: " + response.code() +
                            "id: " + postagemResposta.getId() +
                            "titulo " + postagemResposta.getTitle()
                    );
                }
            }

            @Override
            public void onFailure(Call<Postagem> call, Throwable t) {

            }
        });
    }

    private void recuperarListaRetrofit(){
        DataService service   = retrofit.create(DataService.class);
        Call<List<Foto>> call = service.recuperarFotos();
        call.enqueue(new Callback<List<Foto>>() {
            @Override
            public void onResponse(Call<List<Foto>> call, Response<List<Foto>> response) {
                if( response.isSuccessful() ){
                    listaFotos = response.body();
                    for ( int i = 0; i <listaFotos.size(); i++ ){
                        Foto foto = listaFotos.get(i);
                        Log.d("resultado", "resultado: " + foto.getId() + " / " + foto.getTitle() );
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Foto>> call, Throwable t) {

            }
        });
    }
    
    private void recuperarCEPRetrofit(){
        CEPService service = retrofit.create(CEPService.class);
        Call<CEP> call = service.recuperarCEP("56520000");

        call.enqueue(new Callback<CEP>() {
            @Override
            public void onResponse(Call<CEP> call, Response<CEP> response) {
                if( response.isSuccessful() ){
                    CEP cep = response.body();
                    exibirValorCotacao.setText(cep.getLocalidade() + " - " + cep.getUf());
                }
            }

            @Override
            public void onFailure(Call<CEP> call, Throwable t) {

            }
        });
    }

    private void inicializarComponentes() {
        exibirContacao     = findViewById(R.id.buttonexibirContacao);
        exibirValorCotacao = findViewById(R.id.textExibirValorCotacao);
    }
}