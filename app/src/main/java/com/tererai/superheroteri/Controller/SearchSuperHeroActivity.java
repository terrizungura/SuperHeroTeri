package com.tererai.superheroteri.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;

import com.tererai.superheroteri.R;

public class SearchSuperHeroActivity extends AppCompatActivity {

    EditText editSearch ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_super_hero);

        editSearch = findViewById(R.id.editSearch);

        editSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String newSuperHero = editSearch.getText().toString();
                Intent newHeroIntent = new Intent(SearchSuperHeroActivity.this, ShowSuperHeroActivity.class);
                newHeroIntent.putExtra("SuperHero", newSuperHero);
                startActivity(newHeroIntent);
                return false;
            }
        });
    }
}
