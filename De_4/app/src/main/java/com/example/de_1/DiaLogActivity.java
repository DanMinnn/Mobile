package com.example.de_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.de_1.Db_C2.Db;
import com.example.de_1.databinding.ActivityDiaLogBinding;

public class DiaLogActivity extends AppCompatActivity {
    ActivityDiaLogBinding binding;
    Product product;
    Db db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDiaLogBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = new Db(DiaLogActivity.this);

        getData();
        addEvents();
    }

    private void addEvents() {
        binding.btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.edProductName.getText().toString();
                Double price = Double.valueOf(binding.edProductPrice.getText().toString());

                db.execSql(" UPDATE " + Db.TBL_NAME + " SET " + db.COL_NAME + " = '" + name + "', " + db.COL_PRICE + " = " + price +
                        " WHERE " + db.COL_CODE + " = " + product.getProductCode());


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