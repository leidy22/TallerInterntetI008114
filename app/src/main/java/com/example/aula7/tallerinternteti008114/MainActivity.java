package com.example.aula7.tallerinternteti008114;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.aula7.tallerinternteti008114.Models.Persona;
import com.example.aula7.tallerinternteti008114.Parser.JsonPersona;
import com.example.aula7.tallerinternteti008114.URL.HttpManger;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    TextView textView;
    List<Persona> personaList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        textView = (TextView) findViewById(R.id.textView);

        Task tarea = new Task();
        tarea.execute("http://pastoral.iucesmag.edu.co/practica/listar.php");

    }

    public Boolean isOnLine(){

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if(networkInfo != null){
            return true;
        }else{
            return false;
        }
    }

    public void processData(){
        for (Persona per : personaList){
            textView.append((per.getName() + "\n"));
        }
    }

    public class Task  extends AsyncTask<String, String, String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            String content = null;
            try {
                content = HttpManger.getData(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return  content;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                personaList = JsonPersona.getDataJson(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            processData();
        }
    }
}
