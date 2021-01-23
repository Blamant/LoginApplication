package com.example.loginapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class SearchActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    String s1[], s2[];
    int images[] = {R.drawable.logo_swag_removebg, R.drawable.java_test_logo, R.drawable.logo_swag_removebg, R.drawable.logo_swag_removebg, R.drawable.logo_swag_removebg};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoeken);

        recyclerView = findViewById(R.id.recyclerView);

        s1 = getResources().getStringArray(R.array.kapperszaken);
        s2 = getResources().getStringArray(R.array.description_kapperszaken);

        KapperszaakAdapter kapperszaakAdapter = new KapperszaakAdapter(this, s1, s2, images);
        recyclerView.setAdapter(kapperszaakAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}