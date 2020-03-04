package br.net.noticiasbr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import br.net.noticiasbr.adapter.NoticiasAdapter;
import br.net.noticiasbr.model.Noticia;

public class MainActivity extends AppCompatActivity {

    private ListView lvNoticias;
    private NoticiasAdapter lvAdapter;
    private List<Noticia> noticias = new ArrayList<>();
    private ImageButton btnAtualizar;
    private TextView newsApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        lvNoticias = findViewById(R.id.lvNoticias);
        btnAtualizar = findViewById(R.id.btnAtualizar);
        newsApi = findViewById(R.id.newsApi);
        buscarNoticias();

        btnAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarNoticias();
            }
        });

        lvNoticias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Noticia noticia = (Noticia) lvAdapter.getItem(position);
                Intent intent = new Intent(getBaseContext(), WebActivity.class);
                intent.putExtra("url", noticia.getUrlNoticia());
                startActivity(intent);
            }
        });

        newsApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), WebActivity.class);
                intent.putExtra("url", "https://newsapi.org/");
                startActivity(intent);
            }
        });
    }

    private void buscarNoticias(){
        try {
            URL url = new URL("https://newsapi.org/v2/top-headlines?country=br&apiKey=89cc90fca9254f59906bea7f40a26fe7");
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");
            conexao.connect();
            BufferedReader br = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            String jsonString = br.readLine();
            conexao.disconnect();
            processarDados(jsonString);
        } catch (Exception e) {
            Toast.makeText(getBaseContext(), "Aparentemente o sistema está fora do ar. Tente novamente dentro de alguns instantes.", Toast.LENGTH_LONG).show();
        }
    }
    private void processarDados(String jsonString) throws JSONException {
        noticias.clear();
        JSONObject dados = new JSONObject(jsonString);
        JSONArray artigos = (JSONArray) dados.get("articles");
        for (int i=0; i < artigos.length(); i++) {
            JSONObject artigo = (JSONObject) artigos.get(i);
            JSONObject source = (JSONObject) artigo.get("source");
            Noticia noticia = new Noticia(
                    artigo.getString("urlToImage"),
                    artigo.getString("title"),
                    artigo.getString("description"),
                    source.getString("name"),
                    artigo.getString("publishedAt"),
                    artigo.getString("url")
            );
            noticias.add(noticia);
        }
        lvAdapter = new NoticiasAdapter(this, noticias);
        lvNoticias.setAdapter(lvAdapter);
    }
}
