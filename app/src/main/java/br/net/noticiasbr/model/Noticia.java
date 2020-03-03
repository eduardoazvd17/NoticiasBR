package br.net.noticiasbr.model;

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
        String dt = data.charAt(9) + data.charAt(10) + "/" + data.charAt(6) + data.charAt(7) + "/" + data.charAt(1) + data.charAt(2) + data.charAt(3) + data.charAt(4);
        this.urlImagem = urlImagem;
        this.titulo = titulo;
        this.descricao = descricao;
        this.autor = autor;
        this.data = dt;
        this.urlNoticia = urlNoticia;
    }
}
