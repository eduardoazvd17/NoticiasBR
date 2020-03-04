package br.net.noticiasbr.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.net.noticiasbr.R;
import br.net.noticiasbr.model.Noticia;

public class NoticiasAdapter extends BaseAdapter {

    private List<Noticia> noticias;
    private Activity activity;

    @Override
    public int getCount() {
        return this.noticias.size();
    }

    @Override
    public Object getItem(int position) {
        return this.noticias.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = activity.getLayoutInflater().inflate(R.layout.noticia, parent, false);

        Noticia noticia = noticias.get(position);

        ImageView img = view.findViewById(R.id.imagem);
        TextView titulo = view.findViewById(R.id.titulo);
        TextView descricao = view.findViewById(R.id.descricao);
        TextView autor = view.findViewById(R.id.autor);

        Picasso.with(view.getContext()).load(noticia.getUrlImagem()).into(img);
        titulo.setText(noticia.getTitulo());
        descricao.setText(noticia.getDescricao());
        autor.setText("Fonte: " + noticia.getAutor() + " em " + noticia.getData());

        return view;
    }

    public NoticiasAdapter(Activity activity, List<Noticia> noticias) {
        this.noticias = noticias;
        this.activity = activity;
    }
}
