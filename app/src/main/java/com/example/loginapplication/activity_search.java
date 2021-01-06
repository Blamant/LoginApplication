package com.example.loginapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class activity_search extends AppCompatActivity {

    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ListView listView = findViewById(R.id.my_list
        );
        List<String> mylist = new ArrayList<>();
        mylist.add("Hilversum");
        mylist.add("Utrecht");
        mylist.add("Amsterdam");
        mylist.add("Den Haag");
        mylist.add("Nijmegen");
        mylist.add("Groningen");
        mylist.add("Den Bosch");
        mylist.add("Scheveningen");
        mylist.add("Rotterdam");
        mylist.add("Voorburg");
        mylist.add("Huizen");
        mylist.add("Sneek");
        mylist.add("Maartensdijk");
        mylist.add("Blaricum");
        mylist.add("Baarn");
        mylist.add("Assen");
        mylist.add("Vlissingen");
        mylist.add("Haarlem");
        mylist.add("Leiden");

        arrayAdapter = new ArrayAdapter<>(this, R.layout.my_row, mylist);
        listView.setAdapter(arrayAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.search_icon);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Voer plaatsnaam in");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                arrayAdapter.getFilter().filter(s);
                return true;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }
}