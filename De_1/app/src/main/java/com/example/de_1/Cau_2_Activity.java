package com.example.de_1;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.de_1.Db_C2.Db;
import com.example.de_1.databinding.ActivityCau2Binding;

import java.util.ArrayList;
import java.util.List;

public class Cau_2_Activity extends AppCompatActivity {
    ActivityCau2Binding binding;
    Db db;
    ItemAdapter adapter;
    ArrayList<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCau2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = new Db(Cau_2_Activity.this);
        db.createSampleData();

        loadData();
        addEvents();
    }

    private void addEvents() {
        binding.lvProduct.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Intent iDialog = new Intent(Cau_2_Activity.this, DiaLogActivity.class);
                iDialog.putExtra("product", products.get(position));
                startActivity(iDialog);

                return false;
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData() {
        adapter = new ItemAdapter(Cau_2_Activity.this, R.layout.item, getDataFromDb());
        binding.lvProduct.setAdapter(adapter);
    }

    private List<Product> getDataFromDb() {
        products = new ArrayList<>();

        Cursor cursor = db.getData("SELECT * FROM " + Db.TBL_NAME);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            products.add(new Product(cursor.getInt(0), cursor.getString(1), cursor.getDouble(2)));

            cursor.moveToNext();
        }
        cursor.close();
        return products;
    }
}