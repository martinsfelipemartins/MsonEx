package br.com.msonex.repository;

import android.content.Context;
import android.content.res.AssetManager;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import br.com.msonex.model.FilmesResposta;
import io.reactivex.Single;

public class FilmeRepository {

    public Single<FilmesResposta> obterListaNoticiasDoArquivo(Context context) {
        try {
            AssetManager manager = context.getAssets();
            InputStream newJson = manager.open("filmes.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(newJson));

            Gson gson = new Gson();

            FilmesResposta resposta = gson.fromJson(reader, FilmesResposta.class);

            return Single.just(resposta);

        } catch (IOException e) {
            e.printStackTrace();
            return Single.error(e);
        }
    }
}
