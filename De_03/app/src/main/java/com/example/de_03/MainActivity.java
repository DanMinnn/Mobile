package com.example.de_03;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.de_03.databinding.ActivityMainBinding;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    int count = 0;
    Handler handler = new Handler();
    Random random = new Random();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, 100, 1);
            params.setMargins(10, 10, 10, 10);

            LinearLayout.LayoutParams ln = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            LinearLayout ln_parent = new LinearLayout(MainActivity.this);
            ln_parent.setLayoutParams(ln);
            ln_parent.setOrientation(LinearLayout.VERTICAL);

            LinearLayout currentRow = createNewRow();

            for (int i = 1; i <= 9; i++){
                Button button = new Button(MainActivity.this);
                button.setLayoutParams(params);
                button.setText(String.valueOf(i));
                button.setGravity(Gravity.CENTER);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        binding.edNumber.append(((Button) v).getText().toString());
                    }
                });

                currentRow.addView(button);

                if (i % 3 == 0) {
                    ln_parent.addView(currentRow);
                    if(i != 9) {
                        currentRow = createNewRow();
                    }
                }
            }

            String []lastRowKeys = {"*", "0", "#"};
            currentRow = createNewRow();

            for (String key : lastRowKeys){
                Button button = new Button(MainActivity.this);
                button.setLayoutParams(params);
                button.setText(key);
                button.setGravity(Gravity.CENTER);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        binding.edNumber.append(((Button) v).getText().toString());
                    }
                });

                currentRow.addView(button);

            }

            ln_parent.addView(currentRow);

            binding.lnContainer.addView(ln_parent);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addEvent();
    }

    private LinearLayout createNewRow() {
        LinearLayout newRow = new LinearLayout(MainActivity.this);
        newRow.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        newRow.setLayoutParams(layoutParams);
        return newRow;
    }

    private void addEvent() {
        binding.btnDraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foreBackground();
            }
        });
    }

    private void foreBackground() {
        binding.lnContainer.removeAllViews();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                handler.post(runnable);
                SystemClock.sleep(1000);
            }
        });
        thread.start();
    }


}