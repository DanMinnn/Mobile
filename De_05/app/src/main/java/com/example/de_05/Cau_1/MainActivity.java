package com.example.de_05.Cau_1;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.de_05.R;
import com.example.de_05.databinding.ActivityMainBinding;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    Random random = new Random();
    Handler handler = new Handler();
    int count = 0;
    int flag = 0;
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            LinearLayout.LayoutParams params_TextView_1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100, 1);
            params_TextView_1.setMargins(10, 10, 10, 10);

            LinearLayout.LayoutParams params_TextView_2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100, 2);
            params_TextView_2.setMargins(10, 10, 10, 10);


            LinearLayout.LayoutParams ln = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            LinearLayout linearLayout = new LinearLayout(MainActivity.this);
            linearLayout.setLayoutParams(ln);
            linearLayout.setWeightSum(3);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);

            for (int i = 0; i < 2; i++){
                int rand = random.nextInt(10);

                if (flag % 2 == 0){
                    TextView textView = new TextView(MainActivity.this);
                    textView.setTextSize(16);
                    textView.setGravity(Gravity.CENTER);
                    textView.setText(String.valueOf(rand));

                    if (i % 2 == 0){
                        if (rand % 2 == 0){
                            textView.setLayoutParams(params_TextView_2);
                            textView.setBackgroundColor(Color.BLUE);
                            linearLayout.addView(textView);
                        }else {
                            textView.setLayoutParams(params_TextView_2);
                            textView.setBackgroundColor(Color.GRAY);
                            linearLayout.addView(textView);
                        }
                    }else{
                       if (rand % 2 == 0){
                           textView.setLayoutParams(params_TextView_1);
                           textView.setBackgroundColor(Color.BLUE);
                           linearLayout.addView(textView);
                       }else {
                           textView.setLayoutParams(params_TextView_1);
                           textView.setBackgroundColor(Color.GRAY);
                           linearLayout.addView(textView);
                       }
                    }
                }else {
                    TextView textView = new TextView(MainActivity.this);
                    textView.setTextSize(16);
                    textView.setGravity(Gravity.CENTER);
                    textView.setLayoutParams(params_TextView_1);
                    textView.setText(String.valueOf(rand));

                    if (i % 2 == 0){
                        if (rand % 2 == 0){
                            textView.setLayoutParams(params_TextView_1);
                            textView.setBackgroundColor(Color.BLUE);
                            linearLayout.addView(textView);
                        }else {
                            textView.setLayoutParams(params_TextView_1);
                            textView.setBackgroundColor(Color.GRAY);
                            linearLayout.addView(textView);
                        }
                    }else{
                        if (rand % 2 == 0){
                            textView.setLayoutParams(params_TextView_2);
                            textView.setBackgroundColor(Color.BLUE);
                            linearLayout.addView(textView);
                        }else {
                            textView.setLayoutParams(params_TextView_2);
                            textView.setBackgroundColor(Color.GRAY);
                            linearLayout.addView(textView);
                        }
                    }
                }
            }
            binding.lnContainer.addView(linearLayout);
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

        int numbOfView = Integer.parseInt(binding.edNumber.getText().toString());
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= numbOfView; i++){
                    if(count % 2 == 0){
                        flag++;
                        handler.post(runnable);
                    }

                    if (count == numbOfView)
                        handler.removeCallbacks(runnable);
                    SystemClock.sleep(1000);
                }
            }
        });
        thread.start();
    }
}