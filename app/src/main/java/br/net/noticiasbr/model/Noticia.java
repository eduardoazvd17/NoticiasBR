package br.net.noticiasbr.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Noticia {
    private String urlImagem, titulo, descricao, autor, data, urlNoticia;

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getUrlNoticia() {
        return urlNoticia;
    }

    public void setUrlNoticia(String urlNoticia) {
        this.urlNoticia = urlNoticia;
    }

    public Noticia(String urlImagem, String titulo, String descricao, String autor, String data, String urlNoticia) {
        this.urlImagem = urlImagem;
        this.titulo = titulo;
        this.descricao = descricao;
        this.autor = autor;
        this.data = formatarData(data);
        this.urlNoticia = urlNoticia;
    }

    private String formatarData(String dt) {
        // 2020-03-04T09:31:00Z
        String[] dataParticionada = dt.split("T");
        // 2020-03-04
        String[] dataParticionada2 = dataParticionada[0].split("-");
        // 04/03/2020
        String data = dataParticionada2[2] + "/" + dataParticionada2[1] + "/" + dataParticionada2[0];
        return data;
    }
}
