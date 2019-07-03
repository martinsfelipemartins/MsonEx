package br.com.msonex.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FilmesResposta {

    @SerializedName("filmes")
    @Expose
    private List<Filme> filmes = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public FilmesResposta() {
    }

    /**
     *
     * @param filmes
     */
    public FilmesResposta(List<Filme> filmes) {
        super();
        this.filmes = filmes;
    }

    public List<Filme> getFilmes() {
        return filmes;
    }

    public void setFilmes(List<Filme> filmes) {
        this.filmes = filmes;
    }


}