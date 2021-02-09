package br.com.jobsfotogrid.json.api;

import java.util.List;

import br.com.jobsfotogrid.json.model.Foto;
import br.com.jobsfotogrid.json.model.Postagem;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface DataService {

    //O metódo GET é responsável por recuperar dados em um servidor

    @GET("/photos")
    Call<List<Foto>> recuperarFotos();

    @GET("/posts")
    Call<List<Postagem>> recuperarPostagens();

    //O metódo POST é responsável por salvar dados em um servidor

    @POST("/posts")
    Call<Postagem> salvarPostagem(@Body Postagem postagem);

    @FormUrlEncoded
    @POST("/posts")
    Call<Postagem> salvarPostagem(
        @Field("userId") String userId,
        @Field("title") String title,
        @Field("body") String body
    );

    //O metódo put atualiza todos os campos.
    @PUT("/posts/{id}")
    Call<Postagem> atualizarPostagem(@Path("id") int id, @Body Postagem postagem);

    //O metódo path atualiza apenas os campos que você enviar

    @PUT("/posts/{id}")
    Call<Postagem> atualizarPostagemPath(@Path("id") int id, @Body Postagem postagem);

    //O metódo delete é responsável por excluir os dados
    @DELETE("/posts/{id}")
    Call<Void> removerPostagem(@Path("id") int id);
}
