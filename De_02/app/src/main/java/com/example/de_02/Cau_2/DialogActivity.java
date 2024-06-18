package com.example.de_02.Cau_2;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.de_02.R;
import com.example.de_02.databinding.ActivityDialogBinding;

public class DialogActivity extends AppCompatActivity {
    ActivityDialogBinding binding;
    Product product;
    Db db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDialogBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = new Db(DialogActivity.this);

        getData();
        addEvents();
    }

    private void addEvents() {
        binding.btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean del = db.deleteData(product.productCode);
                if (del)
                    finish();
            }
        });

        binding.btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getData() {
        Intent iGet = getIntent();
        product = (Product) iGet.getSerializableExtra("product");

        binding.edProductCode.setText(String.valueOf(product.productCode));
        binding.edProductName.setText(product.productName);
        binding.edProductPrice.setText(String.format("%.1f", product.productPrice));
    }
}