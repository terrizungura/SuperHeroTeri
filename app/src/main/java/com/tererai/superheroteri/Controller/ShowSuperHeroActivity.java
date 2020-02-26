package com.tererai.superheroteri.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;
import com.tererai.superheroteri.Model.SuperHeroModel;
import com.tererai.superheroteri.R;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class ShowSuperHeroActivity extends AppCompatActivity {

    String SUPER_URL = "https://www.superheroapi.com/api.php/2890307627679486/search/";

    ImageView imgSearch;
    ImageView imgSuperHero;
    TextView txtName;
    TextView txtWork;
    TextView txtWeight;

    TextView txtFullName;
    TextView txtAliases;
    TextView txtPowerstats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_super_hero);

        initializeWidgets();

        searchSuperHero();
    }

    private void searchSuperHero() {
        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowSuperHeroActivity.this, SearchSuperHeroActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();

        Log.d("SuperHero", "onResume called");

        Intent myIntent = getIntent();
        String name = myIntent.getStringExtra("SuperHero");

        if(name!=null){

            SUPER_URL=SUPER_URL+name;
            getNamedSuperHeroInfo(name);
        }
        else{
            getNamedSuperHeroInfo("superman");
        }
    }

    private void getNamedSuperHeroInfo(String name) {

        RequestParams params = new RequestParams();
        params.put("name", name);
        networkToSuperHeroes(params);
    }

    private void initializeWidgets() {
        imgSearch = findViewById(R.id.imgSearch);
        imgSuperHero = findViewById(R.id.imgSuperHero);

        txtName = findViewById(R.id.txtName);
        txtWork = findViewById(R.id.txtWork);
        txtWeight = findViewById(R.id.txtWeight);
        txtFullName = findViewById(R.id.txtFullName);
        txtAliases = findViewById(R.id.txtAliases);
        txtPowerstats = findViewById(R.id.txtPowerstats);
    }

    private void networkToSuperHeroes(RequestParams params){
        final AsyncHttpClient client = new AsyncHttpClient();

        client.get(SUPER_URL, params, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                SuperHeroModel superHeroData = SuperHeroModel.fromJson(response);
                Log.d("SuperHero", "success "+ statusCode );

                    updateUI(superHeroData);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable e, JSONObject response){

                Log.d("SuperHero", "Fail" + e.toString());
                Toast.makeText(ShowSuperHeroActivity.this, "Request Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateUI(SuperHeroModel superData) {
        Log.d("SuperHero", "updating  user interface");
        try {
            txtName.setText("Name: " + superData.getSuperName());
            txtWork.setText("Work: " + superData.getSuperWork());
            txtWeight.setText("Weight: " + superData.getSuperWeight());

            txtAliases.setText("Aliases: "+superData.getAliases());
            txtPowerstats.setText("Power Statistics: "+ superData.getPowerStats());
            txtFullName.setText("Full Name: "+superData.getFullName());

            Picasso.get().load(superData.getSuperUrl()).into(imgSuperHero);
        }
        catch (Exception e){
            e.printStackTrace();
            Toast.makeText(ShowSuperHeroActivity.this, "SuperHero Not Found", Toast.LENGTH_SHORT).show();
            finish();
        }

    }
}
