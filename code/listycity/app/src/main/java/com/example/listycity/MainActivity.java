package com.example.listycity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private EditText editCityName;
    private Button addCityBtn;
    private Button deleteCityBtn;
    private ListView cityList;
    private ArrayList<String> dataList;
    private ArrayAdapter<String> cityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editCityName = findViewById(R.id.editCityName);
        addCityBtn = findViewById(R.id.addCityBtn);
        deleteCityBtn = findViewById(R.id.deleteCityBtn);
        cityList = findViewById(R.id.cityList);

        String[] initialCities = {"Edmonton", "Paris", "London"};
        dataList = new ArrayList<>(Arrays.asList(initialCities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, R.id.textView, dataList);
        cityList.setAdapter(cityAdapter);
        cityList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        addCityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cityName = editCityName.getText().toString().trim();
                if (!cityName.isEmpty()) {
                    dataList.add(cityName);
                    cityAdapter.notifyDataSetChanged();
                    editCityName.setText("");
                }
            }
        });

        deleteCityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = cityList.getCheckedItemPosition();
                if (position != ListView.INVALID_POSITION) {
                    dataList.remove(position);
                    cityAdapter.notifyDataSetChanged();
                    cityList.clearChoices();
                }
            }
        });
    }
}

