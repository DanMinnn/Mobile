package com.example.de_1;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.de_1.databinding.ActivityMainBinding;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    Random rand = new Random();
    int count = 0;

    Handler handler = new Handler();

    Runnable run = new Runnable() {
        @Override
        public void run() {
            //binding.lnContainer.removeAllViews(

            //text view
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, 100, 1);
            params.setMargins(10, 10, 10, 10);

            //ô chứa text view
            LinearLayout.LayoutParams ln = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            LinearLayout ln_parent = new LinearLayout(MainActivity.this);
            ln_parent.setLayoutParams(ln);
            ln_parent.setWeightSum(3);
            ln_parent.setOrientation(LinearLayout.HORIZONTAL);

            for (int item = 0; item < 3; ++item){
                if (count >= Integer.parseInt(binding.edNumber.getText().toString())) break;

                int randNum = rand.nextInt(10);

                TextView textView = new TextView(MainActivity.this);
                textView.setLayoutParams(params);
                textView.setText(String.valueOf(randNum));
                textView.setTextSize(16);
                textView.setTextColor(Color.WHITE);
                textView.setGravity(Gravity.CENTER);

                if(randNum % 2 == 0){
                    textView.setBackgroundColor(Color.BLUE);
                }else
                    textView.setBackgroundColor(Color.GRAY);

                ln_parent.addView(textView);
                count++;
            }
            binding.lnContainer.addView(ln_parent);
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
        count = 0;
        int numberOfView = Integer.parseInt(binding.edNumber.getText().toString());

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < numberOfView; i++){
                    if (count % 3 == 0){
                        handler.post(run);
                    }

                    if(count == numberOfView )
                        handler.removeCallbacks(run);
                    SystemClock.sleep(1000);
                }
            }
        });
        thread.start();
    }
}