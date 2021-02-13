package com.example.exchangerate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<exchange> exchangeArrayList = new ArrayList<exchange>();
    ListView listView;
    listAdapter listAdapter;
    ProgressBar progressBar;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.list_item);
        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.textView);

        Content content = new Content();
        content.execute();
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Content content = new Content();
                content.execute();
            }
        });
    }

    private class Content extends AsyncTask<Void,Void,Void>{

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressBar.setVisibility(View.GONE);
            progressBar.startAnimation(AnimationUtils.loadAnimation(MainActivity.this,android.R.anim.fade_out));
            listAdapter = new listAdapter(MainActivity.this,R.layout.list_item,exchangeArrayList);
            listView.setAdapter(listAdapter);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            progressBar.startAnimation(AnimationUtils.loadAnimation(MainActivity.this,android.R.anim.fade_in));
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
               }

        @Override
        protected Void doInBackground(Void... voids) {
            try{
                String url ="https://www.nbg.gov.ge/index.php?m=582&lng=geo";
                Document doc = Jsoup.connect(url).get();
                Element date = doc.getElementById("currency_id");
                Elements trElements = date.getElementsByTag("tr");
                exchangeArrayList = new ArrayList<exchange>();
                for(int i=0;i<trElements.size();i++){
                    Elements tdElements = trElements.get(i).select("td");
                    String currency = tdElements.get(0).text();
                    String currencyFullName = tdElements.get(1).text();
                    String price = tdElements.get(2).text();
                    String imgUrl ="http://www.nbg.gov.ge/" + tdElements.get(3).select("img").attr("src");
                    String distance = tdElements.get(4).text();
                    exchangeArrayList.add(new exchange(currency,currencyFullName,price,distance,imgUrl));
                }
                } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}
