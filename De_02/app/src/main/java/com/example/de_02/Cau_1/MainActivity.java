package com.example.de_02.Cau_1;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.de_02.databinding.ActivityMainBinding;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    Random random = new Random();
    Handler handler = new Handler();

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(10, 10, 10, 10);

            LinearLayout.LayoutParams ln = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            LinearLayout ln_parent = new LinearLayout(MainActivity.this);
            ln_parent.setLayoutParams(ln);
            ln_parent.setOrientation(LinearLayout.VERTICAL);

            int rand = random.nextInt(101);

            if (rand % 2 == 0){
                Button button = new Button(MainActivity.this);
                button.setLayoutParams(params);
                button.setText(String.valueOf(rand));
                button.setTextSize(16);

                binding.lnContainer.addView(button);
            }else {
                EditText editText = new EditText(MainActivity.this);
                editText.setLayoutParams(params);
                editText.setText(String.valueOf(rand));
                editText.setTextSize(16);

                binding.lnContainer.addView(editText);
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addEvents();
    }

    private void addEvents() {
        binding.btnDraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawBackground();
            }
        });
    }

    private void drawBackground() {
        binding.lnContainer.removeAllViews();
        int numberOfView = Integer.parseInt(binding.edNumber.getText().toString());

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < numberOfView; i++){
                    handler.post(runnable);
                    SystemClock.sleep(1000);
                }
            }
        });
        thread.start();
    }
}