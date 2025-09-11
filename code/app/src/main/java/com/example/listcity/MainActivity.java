package com.example.listcity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;


import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    Button addcity;
    Button deletecity;
    EditText inputCity;
    int selectedcity = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);
        addcity = findViewById(R.id.button);
        deletecity = findViewById(R.id.button2);
        inputCity = findViewById(R.id.input_city);

        String []cities = {"Edmonton", "Vancouver", "Moscow", "Calgary", "Sydney", "Berlin", "Vienna",
                            "Tokyo", "Beijing", "Osaka", "Toronto", "New Delhi", "London", "New York",
                           "Chicago", "Houston", "Montreal", "Istanbul", "Bangkok", "Sao Paulo"};
        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.contents, dataList);
        cityList.setAdapter(cityAdapter);

        cityList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedcity = position;
                cityList.setItemChecked(position,true);
            }
        });
        
        addcity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String newCity = inputCity.getText()== null ? "" : inputCity.getText().toString().trim();
                if (!newCity.isEmpty()) {
                    dataList.add(newCity);
                    cityAdapter.notifyDataSetChanged();
                    inputCity.setText("");
                }
            }

        });

        deletecity.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if (selectedcity>=0 && selectedcity < dataList.size()){
                    dataList.remove(selectedcity);
                    cityAdapter.notifyDataSetChanged();
                    cityList.clearChoices();
                    selectedcity=-1;
                }
            }
        });

    }

}