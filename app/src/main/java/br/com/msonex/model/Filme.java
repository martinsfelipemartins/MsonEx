package br.com.msonex.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Filme implements Parcelable {
    @SerializedName("titulo")
    @Expose
    private String titulo;
    @SerializedName("data")
    @Expose
    private String data;
    @SerializedName("descricao")
    @Expose
    private String descricao;
    @SerializedName("direcao")
    @Expose
    private String direcao;

    public Filme(String titulo, String data, String descricao, String direcao) {
        this.titulo = titulo;
        this.data = data;
        this.descricao = descricao;
        this.direcao = direcao;
    }

    protected Filme(Parcel in) {
        titulo = in.readString();
        data = in.readString();
        descricao = in.readString();
        direcao = in.readString();
    }

    public static final Creator<Filme> CREATOR = new Creator<Filme>() {
        @Override
        public Filme createFromParcel(Parcel in) {
            return new Filme(in);
        }

        @Override
        public Filme[] newArray(int size) {
            return new Filme[size];
        }
    };

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDirecao() {
        return direcao;
    }

    public void setDirecao(String direcao) {
        this.direcao = direcao;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(titulo);
        parcel.writeString(data);
        parcel.writeString(descricao);
        parcel.writeString(direcao);
    }
}
